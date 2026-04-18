package domain;

public class Equipment {
    private int equipmentId;
    private int categoryId;
    private String name;
    private String description;
    private double dailyCost;
    private EquipmentStatus status;

    public Equipment() {}

    public Equipment(int equipmentId, int categoryId, String name, String description,
                     double dailyCost, EquipmentStatus status) {
        this.equipmentId = equipmentId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.dailyCost = dailyCost;
        this.status = status;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getDailyCost() {
        return dailyCost;
    }

    public EquipmentStatus getStatus() {
        return status;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDailyCost(double dailyCost) {
        this.dailyCost = dailyCost;
    }

    public void setStatus(EquipmentStatus status) {
        this.status = status;
    }
// Getters and Setters
}
