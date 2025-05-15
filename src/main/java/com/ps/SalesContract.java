package com.ps;

public class SalesContract extends Contract {
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicle);
        this.isFinanced = isFinanced;
    }

    @Override
    public double getTotalPrice() {
        double basePrice = getVehicle().getPrice();
        double tax = basePrice * 0.05;
        double recordingFee = 100;
        double processingFee = basePrice < 10000 ? 295 : 495;
        return basePrice + tax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) return 0;

        double loanAmount = getTotalPrice();
        if (loanAmount >= 10000) {
            return calculateMonthlyPayment(loanAmount, 0.0425, 48);
        } else {
            return calculateMonthlyPayment(loanAmount, 0.0525, 24);
        }
    }

    private double calculateMonthlyPayment(double amount, double interestRate, int months) {
        double monthlyRate = interestRate / 12;
        return (amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }
}
