package com.ws.probal.messmanagementapplication.service;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.ws.probal.messmanagementapplication.domain.entity.Expense;
import com.ws.probal.messmanagementapplication.domain.entity.MemberVault;
import com.ws.probal.messmanagementapplication.domain.entity.MealEntry;
import com.ws.probal.messmanagementapplication.domain.entity.Member;
import com.ws.probal.messmanagementapplication.domain.response.DailyMealReport;
import com.ws.probal.messmanagementapplication.domain.response.MealReport;
import com.ws.probal.messmanagementapplication.domain.response.MemberMealReport;
import com.ws.probal.messmanagementapplication.repository.ExpenseRepository;
import com.ws.probal.messmanagementapplication.repository.MemberVaultRepository;
import com.ws.probal.messmanagementapplication.repository.MealEntryRepository;
import com.ws.probal.messmanagementapplication.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final ExpenseRepository expenseRepo;
    private final MealEntryRepository mealRepo;
    private final MemberRepository memberRepo;
    private final MemberVaultRepository memberVaultRepo;

    public MealReport getReport(LocalDate fromDate, LocalDate toDate) {
        List<Expense> expenses = expenseRepo.findAllByDateBetween(fromDate, toDate);
        List<MealEntry> meals = mealRepo.findAllByDateBetween(fromDate, toDate);

        double totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
        double totalMeal = meals.stream().mapToDouble(MealEntry::getMealCount).sum();
        double mealRate = totalMeal == 0 ? 0.0 : totalExpense / totalMeal;

        Map<Long, Double> mealByMember = new HashMap<>();
        Map<Long, Double> paidByMember = new HashMap<>();

        for (MealEntry meal : meals) {
            mealByMember.merge(meal.getMember().getId(), meal.getMealCount(), Double::sum);
        }

        for (MemberVault exp : memberVaultRepo.findAll()) {
            paidByMember.merge(exp.getPaidBy().getId(), exp.getAmount(), Double::sum);
        }

        List<Member> members = memberRepo.findAll();
        List<MemberMealReport> memberReports = new ArrayList<>();

        for (Member m : members) {
            double mealsCount = mealByMember.getOrDefault(m.getId(), 0.0);
            double cost = mealsCount * mealRate;
            double paid = paidByMember.getOrDefault(m.getId(), 0.0);
            double balance = paid - cost;

            MemberMealReport mmr = new MemberMealReport();
            mmr.setName(m.getName());
            mmr.setTotalMeal(mealsCount);
            mmr.setMealCost(cost);
            mmr.setAmountPaid(paid);
            mmr.setBalance(balance);

            memberReports.add(mmr);
        }

        // Calculate total given by members
        double totalGivenByMember = memberVaultRepo.findAll().stream()
                .mapToDouble(MemberVault::getAmount).sum();

        MealReport report = new MealReport();
        report.setTotalMeal(totalMeal);
        report.setTotalExpense(totalExpense);
        report.setMealRate(mealRate);
        report.setMemberReports(memberReports);
        report.setTotalGivenByMember(totalGivenByMember);
        report.setAmountInVault(totalGivenByMember - totalExpense);

        return report;
    }

    public byte[] generatePdf(MealReport report) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Mess Settlement Report")
                .setBold().setFontSize(18));

        document.add(new Paragraph("Total Meal: " + report.getTotalMeal()));
        document.add(new Paragraph("Total Given By Members: " + report.getTotalGivenByMember()));
        document.add(new Paragraph("Total Expense: " + report.getTotalExpense()));
        document.add(new Paragraph("Amount In Vault: " + report.getAmountInVault()));
        document.add(new Paragraph("Meal Rate: " + report.getMealRate()));

        float[] columnWidths = {150F, 70F, 100F, 80F, 80F};
        Table table = new Table(columnWidths);

        table.addHeaderCell("Name");
        table.addHeaderCell("Meals");
        table.addHeaderCell("Meal Cost");
        table.addHeaderCell("Paid");
        table.addHeaderCell("Balance");

        for (var m : report.getMemberReports()) {
            table.addCell(m.getName());
            table.addCell(String.valueOf(m.getTotalMeal()));
            table.addCell(String.format("%.2f", m.getMealCost()));
            table.addCell(String.valueOf(m.getAmountPaid()));
            table.addCell(String.format("%.2f", m.getBalance()));
        }

        document.add(table);
        document.close();
        return out.toByteArray();
    }

    public List<DailyMealReport> getDailyMealReport(LocalDate from, LocalDate to) {
        List<MealEntry> meals = mealRepo.findAllByDateBetween(from, to);

        Map<LocalDate, Map<String, Double>> reportMap = new TreeMap<>();

        for (MealEntry meal : meals) {
            LocalDate date = meal.getDate();
            String memberName = meal.getMember().getName();
            double count = meal.getMealCount();

            reportMap.computeIfAbsent(date, d -> new HashMap<>()).merge(memberName, count, Double::sum);
        }

        return reportMap.entrySet().stream()
                .map(entry -> new DailyMealReport(entry.getKey(), entry.getValue()))
                .toList();
    }

    public byte[] generateDailyMealMatrixPdf(List<DailyMealReport> dailyReports) throws IOException {
        Set<String> allMembers = new TreeSet<>();

        for (DailyMealReport r : dailyReports) {
            allMembers.addAll(r.getMeals().keySet());
        }

        List<String> memberList = new ArrayList<>(allMembers);
        Map<String, Double> totalPerMember = new HashMap<>();
        for (String member : memberList) {
            totalPerMember.put(member, 0.0);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Daily Meal Report (Matrix Format)")
                .setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));

        float[] columnWidths = new float[memberList.size() + 1];
        columnWidths[0] = 100F; // Date column
        Arrays.fill(columnWidths, 1, columnWidths.length, 80F);

        Table table = new Table(columnWidths);

        table.addHeaderCell("Date");
        memberList.forEach(name -> table.addHeaderCell(new Cell().add(new Paragraph(name))));

        for (DailyMealReport daily : dailyReports) {
            table.addCell(daily.getDate().toString());
            for (String member : memberList) {
                double count = daily.getMeals().getOrDefault(member, 0.0);
                totalPerMember.put(member, totalPerMember.get(member) + count);
                table.addCell(String.valueOf(count));
            }
        }

        // Add total row
        table.addCell(new Cell().add(new Paragraph("Total")).setBold());
        for (String member : memberList) {
            double total = totalPerMember.get(member);
            table.addCell(new Cell().add(new Paragraph(String.valueOf(total))).setBold());
        }

        document.add(table);
        document.close();

        return out.toByteArray();
    }
}
