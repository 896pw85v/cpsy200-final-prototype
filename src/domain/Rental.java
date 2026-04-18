package domain;


import java.util.Date;
import java.util.List;

public class Rental {
    private int rentalId;
    private Date rentalDate;
    private Date returnDate;
    private List<Equipment> equipmentList;
    private double totalCost;

    public Rental() {}

    public Rental(int rentalId, Date rentalDate, Date returnDate,
                  List<Equipment> equipmentList, double totalCost) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.equipmentList = equipmentList;
        this.totalCost = totalCost;
    }

    // Getters and Setters
}