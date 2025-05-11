package com.ws.probal.messmanagementapplication.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyMealReport {
    private LocalDate date;
    private Map<String, Double> meals;
}
