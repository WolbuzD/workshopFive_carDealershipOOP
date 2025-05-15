package com.ps;

public class LeaseContract extends Contract {

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
    }

    @Override
    public double getTotalPrice() {
        double originalPrice = getVehicle().getPrice();
        double endingValue = originalPrice * 0.5;
        double leaseFee = originalPrice * 0.07;
        return endingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double amount = getTotalPrice();
        return calculateMonthlyPayment(amount, 0.04, 36);
    }

    private double calculateMonthlyPayment(double amount, double interestRate, int months) {
        double monthlyRate = interestRate / 12;
        return (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }
}
