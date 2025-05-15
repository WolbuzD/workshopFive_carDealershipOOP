package com.ps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ContractFileManager {
    private final String filePath = "contracts.csv";

    public void saveContract(Contract contract) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(filePath, true)); // Open in append mode
            StringBuilder line = new StringBuilder();

            if (contract instanceof SalesContract sales) {
                line.append("SALE").append("|")
                        .append(sales.getDate()).append("|")
                        .append(sales.getCustomerName()).append("|")
                        .append(sales.getCustomerEmail()).append("|")
                        .append(vehicleDetails(sales.getVehicle())).append("|")
                        .append(String.format("%.2f", sales.getTotalPrice())).append("|")
                        .append(sales.isFinanced() ? "YES" : "NO").append("|")
                        .append(String.format("%.2f", sales.getMonthlyPayment()));
            } else if (contract instanceof LeaseContract lease) {
                line.append("LEASE").append("|")
                        .append(lease.getDate()).append("|")
                        .append(lease.getCustomerName()).append("|")
                        .append(lease.getCustomerEmail()).append("|")
                        .append(vehicleDetails(lease.getVehicle())).append("|")
                        .append(String.format("%.2f", lease.getTotalPrice())).append("|")
                        .append(String.format("%.2f", lease.getMonthlyPayment()));
            }

            writer.write(line.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }


    private String vehicleDetails(Vehicle v) {
        return v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" +
                v.getVehicleType() + "|" + v.getColor() + "|" + String.format("%.2f", v.getPrice());
    }
}

