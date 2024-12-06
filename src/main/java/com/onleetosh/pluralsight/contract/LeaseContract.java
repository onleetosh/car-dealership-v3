package com.onleetosh.pluralsight.contract;

import com.onleetosh.pluralsight.dealership.*;
import com.onleetosh.pluralsight.util.*;

public class LeaseContract extends Contract {

    /**
     * Lease Information
     */
    private double expectEndingValue; //  e = p * (50/100)
    private double leaseFee; // f = p * (7/100)

    private double expectEndingValuePercentage = 0.5;
    private double leaseFeePercentage = 0.07;

    /**
     * Constructor One: used to initialize a Lease contract; requires minimal information and the missing
     * values are computed based on the vehicle's price.
     */
    public LeaseContract(String date,
                         String customerName,
                         String customerEmail,
                         Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectEndingValue = vehicleSold.getPrice() * expectEndingValuePercentage;
        this.leaseFee = vehicleSold.getPrice() * leaseFeePercentage;
    }

    /**
     * Constructor Two: used to initialize a Lease contract; requires all contract details
     */

    public LeaseContract(String date,
                         String customerName,
                         String customerEmail,
                         Vehicle vehicleSold,
                         double expectEndingValue,
                         double leaseFee) {
        super(date, customerName, customerEmail, vehicleSold);
        this.leaseFee = leaseFee;
        this.expectEndingValue = expectEndingValue;
    }



    /**
     * Getters
     */

    public double getExpectEndingValue() {
        return expectEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    /**
     * Setters
     */
    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public void setExpectEndingValue(double expectEndingValue) {
        this.expectEndingValue = expectEndingValue;
    }

    /**
     * Override to calculate and return the value amount for total price and monthly payment
     */

    //all leases are financed at 4.0% for 36 mos
    @Override
    public double getTotalPrice() {
        return expectEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double financeRate = 0.04;
        double financeTerm = 36;
        return Calculation.calculateLoanPayment(getTotalPrice(), financeRate, financeTerm);
    }

    /**
     * Override to convert and return Sales contract as a custom string
     */
    @Override
    public String toString() {
        // 0  1  2  3  4  5  6  7  8  9  10  11   12   13   14  15
        return String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",
                this.date,
                this.customerName,
                this.customerEmail,
                this.vehicleSold.getVin(),
                this.vehicleSold.getYear(),
                this.vehicleSold.getMake(),
                this.vehicleSold.getModel(),
                this.vehicleSold.getVehicleType(),
                this.vehicleSold.getColor(),
                this.vehicleSold.getOdometer(),
                this.vehicleSold.getPrice(),
                this.expectEndingValue,
                this.leaseFee,
                getTotalPrice(),
                getMonthlyPayment());
    }
}