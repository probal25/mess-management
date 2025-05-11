package com.ws.probal.messmanagementapplication.service;


import com.ws.probal.messmanagementapplication.domain.entity.MemberVault;
import com.ws.probal.messmanagementapplication.domain.entity.MealEntry;
import com.ws.probal.messmanagementapplication.repository.MemberVaultRepository;
import com.ws.probal.messmanagementapplication.repository.MealEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final MemberVaultRepository expenseRepo;
    private final MealEntryRepository mealRepo;

    public double calculateMealRate(LocalDate fromDate, LocalDate toDate) {
        double totalExpense = expenseRepo.findAllByDateBetween(fromDate, toDate)
                .stream()
                .mapToDouble(MemberVault::getAmount)
                .sum();

        double totalMeals = mealRepo.findAllByDateBetween(fromDate, toDate)
                .stream()
                .mapToDouble(MealEntry::getMealCount)
                .sum();

        return totalMeals == 0 ? 0.0 : totalExpense / totalMeals;
    }
}
