package com.ws.probal.messmanagementapplication.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealReport {
    private double totalGivenByMember;
    private double totalExpense;
    private double amountInVault;
    private double totalMeal;
    private double mealRate;
    private List<MemberMealReport> memberReports;
}
