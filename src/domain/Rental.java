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

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
