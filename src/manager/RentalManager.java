package manager;

import domain.Customer;
import domain.Equipment;
import domain.EquipmentStatus;
import domain.Rental;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalManager {

    private List<Rental> rentals = new ArrayList<>();
    private int nextRentalId = 1;

    public void createRental(Customer c, List<Equipment> items) {

        if (c.isBanned()) {
            System.out.println("Customer is banned!");
            return;
        }

        // Check all equipment availability
        for (Equipment e : items) {
            if (e.getStatus() != EquipmentStatus.AVAILABLE) {
                System.out.println("Equipment not available: " + e.getName());
                return;
            }
        }

        // Create rental
        Rental rental = new Rental();
        rental.setRentalId(nextRentalId++);
        rental.setRentalDate(new Date());
        rental.setEquipmentList(items);

        // Mark all equipment as rented
        for (Equipment e : items) {
            e.setStatus(EquipmentStatus.RENTED);
        }

        rentals.add(rental);

        System.out.println("Rental created successfully!");
    }

    public void returnRental(int id, Date returnDate) {

        for (Rental r : rentals) {
            if (r.getRentalId() == id) {

                r.setReturnDate(returnDate);

                // Mark all equipment as available again
                for (Equipment e : r.getEquipmentList()) {
                    e.setStatus(EquipmentStatus.AVAILABLE);
                }

                System.out.println("Rental returned successfully!");
                return;
            }
        }

        System.out.println("Rental not found!");
    }

    public List<Rental> getAllRentals() {
        return rentals;
    }
}
