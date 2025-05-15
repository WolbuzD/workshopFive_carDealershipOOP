package com.ps;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {
    private static final String INVENTORY_FILE = "inventory.csv";

    // Load the dealership and its vehicles from inventory.csv
    public Dealership getDealership() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INVENTORY_FILE))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                System.out.println("Inventory file is empty.");
                return null;
            }

            String[] header = headerLine.split("\\|");
            Dealership dealership = new Dealership(header[0], header[1], header[2]);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");
                int vin = Integer.parseInt(data[0]);
                int year = Integer.parseInt(data[1]);
                String make = data[2];
                String model = data[3];
                String type = data[4];
                String color = data[5];
                int odometer = Integer.parseInt(data[6]);
                double price = Double.parseDouble(data[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

            return dealership;

        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
            return null;
        }
    }

    // Save all dealership vehicles back to inventory.csv
    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(INVENTORY_FILE))) {
            // Save dealership info (first line)
            writer.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());

            // Save each vehicle on a new line
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.println(
                        v.getVin() + "|" +
                                v.getYear() + "|" +
                                v.getMake() + "|" +
                                v.getModel() + "|" +
                                v.getVehicleType() + "|" +
                                v.getColor() + "|" +
                                v.getOdometer() + "|" +
                                String.format("%.2f", v.getPrice())
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving inventory file: " + e.getMessage());
        }
    }
}
