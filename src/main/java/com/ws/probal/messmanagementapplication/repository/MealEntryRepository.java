package com.ws.probal.messmanagementapplication.repository;

import com.ws.probal.messmanagementapplication.domain.entity.MealEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealEntryRepository extends JpaRepository<MealEntry, Long> {
    List<MealEntry> findAllByDateBetween(LocalDate start, LocalDate end);
}
