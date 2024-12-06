package com.onleetosh.pluralsight.test;

import com.onleetosh.pluralsight.util.Calculation;
import com.onleetosh.pluralsight.util.Console;


public class test2 {


        public static void main(String[] args) {


            while (true) {
                double price = Console.PromptForDouble("\n\nEntering price.. ");
                double leasingFee = price * 0.07;  // 7% of the original price
                double endValue = price * 0.5;  // 50% of the original price

                // Calculate total cost including leasing fee and end value
                double totalCost = endValue + leasingFee;

                // Finance terms
                int financeTerm = 36;  // 36 months
                double financeRate = 0.04;  // 4% annual rate

                // Calculate the monthly payment based on total cost
                double monthlyPayment = Calculation.calculateLoanPayment(totalCost, financeRate, financeTerm);

                // Output the details
                System.out.println("\nDetails:");
                System.out.printf("\nEnd Value: %.2f", endValue);
                System.out.printf("\nLeasing Fee: %.2f", leasingFee);
                System.out.printf("\nTotal Cost: %.2f", totalCost);
                System.out.printf("\nFinance Rate: %.2f%%", financeRate * 100);
                System.out.printf("\nFinance Term: %d months", financeTerm);
                System.out.printf("\nMonthly Payment: %.2f", monthlyPayment);
            }

        }
}
