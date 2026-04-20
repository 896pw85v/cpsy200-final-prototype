package app;

import manager.*;
import domain.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Handling {
    CategoryManager categoryManager;
    CustomerManager customerManager;
    EquipmentManager equipmentManager;
    RentalManager rentalManager;
    ReportManager reportManager;

    public Handling() {
        this.categoryManager = new CategoryManager();
        this.customerManager = new CustomerManager();
        this.equipmentManager = new EquipmentManager();
        this.rentalManager = new RentalManager();
        this.reportManager = new ReportManager();
    }

    public String handleAddEquip(String[] args) {
        String res;

        try {
            int eId = Integer.parseInt(args[0]);
            int cId = Integer.parseInt(args[1]);
            String name = args[2];
            String des = args[3];
            double dc = Double.parseDouble(args[4]);
            EquipmentStatus es = EquipmentStatus.valueOf(args[5].toUpperCase());
            Equipment e = new Equipment(eId, cId, name, des, dc, es);

            equipmentManager.addEquipment(e);
            res = "Added equipment: " + String.join(", ", args);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            res = "Failed to add this equipment. Please try again. ";
        }

        return res;
    }

    public String handleDeleteEquipment(String[] args) {
        try {
            int id = Integer.parseInt(args[0].trim());
            equipmentManager.removeEquipment(id);
            return "Equipment ID " + id + " marked as removed.";
        } catch (Exception ex) {
            return "Failed to delete equipment: " + ex.getMessage();
        }
    }


    // ── Add Customer ──────────────────────────────────────────────────────────
    // args: customerId, firstName, lastName, phone, email, discountRate
    public String handleAddCustomer(String[] args) {
        try {
            int customerId     = Integer.parseInt(args[0].trim());
            String firstName   = args[1].trim();
            String lastName    = args[2].trim();
            String phone       = args[3].trim();
            String email       = args[4].trim();
            double discountRate = Double.parseDouble(args[5].trim());

            Customer c = new Customer(customerId, firstName, lastName, phone, email, discountRate, false);
            customerManager.addCustomer(c);
            return "Customer added: " + firstName + " " + lastName;
        } catch (Exception ex) {
            return "Failed to add customer: " + ex.getMessage();
        }
    }

    // ── Get All Equipment ─────────────────────────────────────────────────────
    public String handleGetAllEquipment() {
        List<Equipment> list = equipmentManager.listEquipment();
        if (list.isEmpty()) return "No equipment found.";

        StringBuilder sb = new StringBuilder("=== Equipment List ===\n");
        for (Equipment e : list) {
            sb.append("ID: ").append(e.getEquipmentId())
                    .append(" | Category: ").append(e.getCategoryId())
                    .append(" | Name: ").append(e.getName())
                    .append(" | $").append(e.getDailyCost()).append("/day")
                    .append(" | Status: ").append(e.getStatus())
                    .append("\n");
        }
        return sb.toString();
    }

    // ── Get All Customers ─────────────────────────────────────────────────────
    public String handleGetAllCustomers() {
        List<Customer> list = customerManager.listCustomers();
        if (list.isEmpty()) return "No customers found.";

        StringBuilder sb = new StringBuilder("=== Customer List ===\n");
        for (Customer c : list) {
            sb.append("ID: ").append(c.getCustomerId())
                    .append(" | ").append(c.getFirstName()).append(" ").append(c.getLastName())
                    .append(" | Phone: ").append(c.getPhone())
                    .append(" | Email: ").append(c.getEmail())
                    .append(" | Discount: ").append(c.getDiscountRate())
                    .append(" | Banned: ").append(c.isBanned())
                    .append("\n");
        }
        return sb.toString();
    }

    // ── Process Rental ────────────────────────────────────────────────────────
    // args: customerId, equipmentId1, equipmentId2, ... (one or more equipment IDs)
    public String handleProcessRental(String[] args) {
        try {
            int customerId = Integer.parseInt(args[0].trim());
            Customer customer = customerManager.findCustomerById(customerId);
            if (customer == null) return "Customer ID " + customerId + " not found.";

            List<Equipment> items = new ArrayList<>();
            for (int i = 1; i < args.length; i++) {
                int equipId = Integer.parseInt(args[i].trim());
                Equipment e = equipmentManager.findEquipmentById(equipId);
                if (e == null) return "Equipment ID " + equipId + " not found.";
                items.add(e);
            }

            rentalManager.createRental(customer, items);
            return "Rental created successfully. ";
        } catch (Exception ex) {
            return "Failed to process rental: " + ex.getMessage();
        }
    }

    // ── Get Rentals for Customer ──────────────────────────────────────────────
    // args: customerId
    public String handleGetRentalsForCustomer(String[] args) {
        try {
            int customerId = Integer.parseInt(args[0].trim());
            Customer c = customerManager.findCustomerById(customerId);
            List<Equipment> items = new ArrayList<>();
            for (int i = 1; i < args.length; i++) {
                int id = Integer.parseInt(args[i]);
                items.add(equipmentManager.findEquipmentById(id));
            }
            rentalManager.createRental(c, items);
            return "Rental created successfully. ";
        } catch (Exception ex) {
            return "Failed to get rentals: " + ex.getMessage();
        }
    }
}
