package com.ws.probal.messmanagementapplication.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberMealReport {
    private String name;
    private double totalMeal;
    private double mealCost;
    private double amountPaid;
    private double balance;
}
