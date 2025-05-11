package com.ws.probal.messmanagementapplication.repository;

import com.ws.probal.messmanagementapplication.domain.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByDateBetween(LocalDate start, LocalDate end);
}
