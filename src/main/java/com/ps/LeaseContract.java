package com.ps;

public class LeaseContract extends Contract {

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    @Override
    public double getTotalPrice() {
        return getEndingValue() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        return calculateMonthlyPayment(getTotalPrice(), 0.04, 36);
    }

    private double calculateMonthlyPayment(double amount, double interestRate, int months) {
        double monthlyRate = interestRate / 12;
        return (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }

    // 🔹 Added helper methods to match file output format
    public double getEndingValue() {
        return getVehicle().getPrice() * 0.5;
    }

    public double getLeaseFee() {
        return getVehicle().getPrice() * 0.07;
    }

    public double getTotalLeaseCost() {
        return getTotalPrice();
    }
}
