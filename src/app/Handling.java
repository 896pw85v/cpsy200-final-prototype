package app;

import manager.*;
import domain.*;

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
}
