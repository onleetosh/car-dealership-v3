package com.onleetosh.pluralsight;

import com.onleetosh.pluralsight.util.Calculation;
import com.onleetosh.pluralsight.util.Console;

import static com.onleetosh.pluralsight.util.Calculation.calculateLoanPayment;

public class Tesst {


    public static void main(String[] args) {

        while (true) {
            final double salesTaxPercentage = 0.05;
            final double recordingFee = 100;

            double price = Console.PromptForDouble("\n\nEntering price.. ");
            double salesTax = price * salesTaxPercentage;

            double processFee;

            if (price < 10000) {
                processFee = 295;
            } else {
                processFee = 495;
            }

            //prompt for finance option

            boolean financeOption = Console.PromptForYesNo("Do you want to fiance");


            double totalCost = 0;
            double monthlyPayment = 0;

            //monthly payment is based on .. all loans are at 4.25% for 48 months if the price if $10,000
            // or more. otherwise they are at 5.25% for 24 months

            //if user enter yes to finance
            if (financeOption == true) {
                // Finance rate and term
                double financeRate = (price < 10000) ? 0.0525 : 0.0425;  // Interest rate
                int financeTerm = (price < 10000) ? 24 : 48;  // Loan term

                // Calculate total cost including all fees and taxes
                totalCost = price + salesTax + recordingFee + processFee;

                // Calculate monthly payment based on total cost
                monthlyPayment = calculateLoanPayment(totalCost, financeRate, financeTerm);

                // Output the details
                System.out.println("\nDetails:");
                System.out.printf("\nSales Tax: %.2f", salesTax);
                System.out.printf("\nProcessing Fee: %.2f", processFee);
                System.out.printf("\nRecording Fee: %.2f", recordingFee);
                System.out.printf("\nTotal Cost: %.2f", totalCost);
                System.out.printf("\nFinance Rate: %.2f%%", financeRate * 100);
                System.out.printf("\nFinance Term: %d months", financeTerm);
                System.out.printf("\nMonthly Payment: %.2f", monthlyPayment);
            }

            //if user does not want to finance
            else {
                totalCost = price + salesTax + recordingFee + processFee;

                System.out.println("\nDetails ");
                System.out.printf("\nSale Tax: %.2f", salesTax);
                System.out.printf("\nProcessing fee: %.2f", processFee);
                System.out.printf("\nRecording Fee fee: %.2f", recordingFee);
                System.out.printf("\nTotal Cost %.2f", totalCost);
                System.out.println("\nFinance " + financeOption);
                System.out.printf("\nLoan Payment: %.2f", monthlyPayment);
            }

        }
    }



}
