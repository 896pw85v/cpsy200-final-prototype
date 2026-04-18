package domain;


public class Category {
    private int categoryId;
    private String description;

    public Category() {}

    public Category(int categoryId, String description) {
        this.categoryId = categoryId;
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

// Getters and Setters
}