package manager;

import domain.Equipment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReportManager {

    public void salesByDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("\n==========================================");
        System.out.println(" SALES REPORT FOR DATE: " + sdf.format(date));
        System.out.println("==========================================");
        System.out.println("Data would be aggregated from Rentals...");
        System.out.println("==========================================\n");
    }

    public void salesByCustomer(int id) {
        System.out.println("\n==========================================");
        System.out.println(" SALES REPORT FOR CUSTOMER ID: " + id);
        System.out.println("==========================================");
        System.out.println("Data would be aggregated from Rentals...");
        System.out.println("==========================================\n");
    }

    public void listByCategory(int categoryId) {
        System.out.println("\n==========================================");
        System.out.println(" EQUIPMENT IN CATEGORY ID: " + categoryId);
        System.out.println("==========================================");
        
        EquipmentManager em = new EquipmentManager();
        List<Equipment> equipment = em.listEquipment();
        boolean found = false;
        
        if (equipment != null && !equipment.isEmpty()) {
            for (Equipment e : equipment) {
                if (e.getCategoryId() == categoryId) {
                    System.out.println("ID: " + e.getEquipmentId() + " | Name: " + e.getName() + 
                                       " | Cost: $" + e.getDailyCost() + " | Status: " + e.getStatus());
                    found = true;
                }
            }
        }
        
        if (!found) {
            System.out.println("No equipment currently found for this category.");
        }
        System.out.println("==========================================\n");
    }

    public void customReport(Object params) {
        System.out.println("\n==========================================");
        System.out.println(" CUSTOM REPORT ");
        System.out.println("==========================================");
        System.out.println("Generating report with parameters: " + params.toString());
        System.out.println("==========================================\n");
    }
}