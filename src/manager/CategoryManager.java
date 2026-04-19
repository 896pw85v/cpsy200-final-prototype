package manager;

 

import domain.Category;

 

import java.io.*;
import java.util.ArrayList;
import java.util.List;

 

public class CategoryManager {

 

    private static final String FILE_PATH = "data/categories.csv";

 

    // ── Load all categories from CSV ──────────────────────────────────────────
    private List<Category> loadCategories() {
        List<Category> categories = new ArrayList<>();
        File file = new File(FILE_PATH);

 

        if (!file.exists()) return categories;

 

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

 

                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;

 

                int id = Integer.parseInt(parts[0].trim());
                String description = parts[1].trim();
                categories.add(new Category(id, description));
            }
        } catch (IOException e) {
            System.err.println("Error reading categories file: " + e.getMessage());
        }

 

        return categories;
    }

 

    // ── Save all categories to CSV ────────────────────────────────────────────
    private void saveCategories(List<Category> categories) {
        // Make sure the data/ folder exists
        new File("data").mkdirs();

 

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Category c : categories) {
                writer.write(c.getCategoryId() + "," + c.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving categories file: " + e.getMessage());
        }
    }

 

    // ── Add a new category ────────────────────────────────────────────────────
    public void addCategory(Category c) {
        List<Category> categories = loadCategories();

 

        // Check for duplicate ID
        for (Category existing : categories) {
            if (existing.getCategoryId() == c.getCategoryId()) {
                System.out.println("Category with ID " + c.getCategoryId() + " already exists.");
                return;
            }
        }

 

        categories.add(c);
        saveCategories(categories);
        System.out.println("Category added: " + c.getCategoryId() + " - " + c.getDescription());
    }

 

    // ── Delete a category by ID ───────────────────────────────────────────────
    public void deleteCategory(int categoryId) {
        List<Category> categories = loadCategories();
        boolean removed = categories.removeIf(c -> c.getCategoryId() == categoryId);

 

        if (removed) {
            saveCategories(categories);
            System.out.println("Category " + categoryId + " removed.");
        } else {
            System.out.println("Category " + categoryId + " not found.");
        }
    }

 

    // ── List all categories ───────────────────────────────────────────────────
    public List<Category> listCategories() {
        return loadCategories();
    }

 

    // ── Find a category by ID ─────────────────────────────────────────────────
    public Category findCategoryById(int categoryId) {
        for (Category c : loadCategories()) {
            if (c.getCategoryId() == categoryId) return c;
        }
        return null;
    }
}
