package com.onleetosh.pluralsight.util;

public class Calculation {

    public static double calculateLoanPayment(double borrowedAmount, double loanRate, double months) {
        return borrowedAmount * (loanRate / 12 * Math.pow(1 + loanRate / 12, months))
                / (Math.pow(1 + loanRate / 12, months) - 1);
    }

    public static double calculateMonthlyPayment(double borrowedAmount, double annualRate, int months) {
        double monthlyRate = annualRate / 12;  // Convert annual rate to monthly rate
        // Loan payment formula
        return borrowedAmount * (monthlyRate * Math.pow(1 + monthlyRate, months))
                / (Math.pow(1 + monthlyRate, months) - 1);
    }
}