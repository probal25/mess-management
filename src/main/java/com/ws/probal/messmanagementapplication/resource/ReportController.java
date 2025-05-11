package com.ws.probal.messmanagementapplication.resource;

import com.ws.probal.messmanagementapplication.domain.response.DailyMealReport;
import com.ws.probal.messmanagementapplication.domain.response.MealReport;
import com.ws.probal.messmanagementapplication.service.ReportService;
import com.ws.probal.messmanagementapplication.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class ReportController {

    private final ReportService reportService;
    private final SettlementService settlementService;

    @GetMapping("/meal-rate")
    public ResponseEntity<Double> getMealRate(@RequestParam String from, @RequestParam String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return ResponseEntity.ok(reportService.calculateMealRate(fromDate, toDate));
    }

    @GetMapping("/settlement")
    public ResponseEntity<MealReport> getSettlementReport(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

        return ResponseEntity.ok(settlementService.getReport(from, to));
    }

    @PostMapping(value = "/settlement/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdfReport(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) throws IOException {

        var report = settlementService.getReport(from, to);
        byte[] pdfBytes = settlementService.generatePdf(report);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.inline().filename("mess_report.pdf").build());
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    @GetMapping("/daily-meals")
    public ResponseEntity<List<DailyMealReport>> getDailyMeals(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return ResponseEntity.ok(settlementService.getDailyMealReport(from, to));
    }

    @PostMapping(value = "/daily-meals/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getDailyMealsPdf(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) throws IOException {

        List<DailyMealReport> dailyReports = settlementService.getDailyMealReport(from, to);
        byte[] pdf = settlementService.generateDailyMealMatrixPdf(dailyReports);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.inline().filename("daily_meal_matrix.pdf").build());
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
