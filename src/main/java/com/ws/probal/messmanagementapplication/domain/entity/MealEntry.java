package com.ws.probal.messmanagementapplication.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "meal_entry")
@NoArgsConstructor
@AllArgsConstructor
public class MealEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double mealCount;
    @ManyToOne
    private Member member;
}
