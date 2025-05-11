package com.ws.probal.messmanagementapplication.service;


import com.ws.probal.messmanagementapplication.domain.entity.Expense;
import com.ws.probal.messmanagementapplication.domain.request.ExpenseRequest;
import com.ws.probal.messmanagementapplication.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense addExpense(ExpenseRequest request) {
        Expense expense = new Expense(null, request.getAmount(), request.getDate());
        return expenseRepository.save(expense);
    }
}
