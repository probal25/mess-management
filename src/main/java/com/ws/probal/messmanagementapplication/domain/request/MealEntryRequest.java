package com.ws.probal.messmanagementapplication.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealEntryRequest {
    private Long memberId;
    private LocalDate date;
    private double mealCount;
}
