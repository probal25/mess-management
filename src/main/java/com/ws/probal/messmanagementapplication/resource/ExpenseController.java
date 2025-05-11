package com.ws.probal.messmanagementapplication.resource;


import com.ws.probal.messmanagementapplication.domain.entity.Expense;
import com.ws.probal.messmanagementapplication.domain.request.ExpenseRequest;
import com.ws.probal.messmanagementapplication.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseRequest request) {
        Expense expense = expenseService.addExpense(request);
        return ResponseEntity.ok(expense);
    }
}
