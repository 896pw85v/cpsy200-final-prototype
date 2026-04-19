package manager;



import domain.Customer;



import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class CustomerManager {



private static final String FILE_PATH = "data/customers.csv";



// ── Load all customers from CSV ───────────────────────────────────────────
private List<Customer> loadCustomers() {
 List<Customer> customers = new ArrayList<>();
 File file = new File(FILE_PATH);

if (!file.exists()) return customers;

try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
 String line;
 while ((line = reader.readLine()) != null) {
 line = line.trim();
 if (line.isEmpty()) continue;


 String[] parts = line.split(",", 7);
 if (parts.length < 7) continue;


 int id= Integer.parseInt(parts[0].trim());
 String firstName = parts[1].trim();
 String lastName = parts[2].trim();
 String phone= parts[3].trim();
 String email= parts[4].trim();
 double discount = Double.parseDouble(parts[5].trim());
 boolean isBanned = Boolean.parseBoolean(parts[6].trim());


 customers.add(new Customer(id, firstName, lastName, phone, email, discount, isBanned));
 }
 } catch (IOException e) {
 System.err.println("Error reading customers file: " + e.getMessage());
 }


 return customers;
}


// ── Save all customers to CSV ─────────────────────────────────────────────
private void saveCustomers(List<Customer> customers) {
 new File("data").mkdirs();


 try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
 for (Customer c : customers) {
 writer.write(
c.getCustomerId() + "," +
c.getFirstName() + "," +
c.getLastName()+ "," +
c.getPhone()+ "," +
c.getEmail()+ "," +
c.getDiscountRate() + "," +
c.isBanned()
 );
 writer.newLine();
 }
 } catch (IOException e) {
 System.err.println("Error saving customers file: " + e.getMessage());
 }
}


// ── Add a new customer ────────────────────────────────────────────────────
public void addCustomer(Customer c) {
 List<Customer> customers = loadCustomers();


 for (Customer existing : customers) {
 if (existing.getCustomerId() == c.getCustomerId()) {
 System.out.println("Customer with ID " + c.getCustomerId() + " already exists.");
 return;
 }
 }


 customers.add(c);
 saveCustomers(customers);
 System.out.println("Customer added: " + c.getFirstName() + " " + c.getLastName());
}


// ── Update an existing customer ───────────────────────────────────────────
public void updateCustomer(Customer c) {
 List<Customer> customers = loadCustomers();
 boolean found = false;


 for (int i = 0; i < customers.size(); i++) {
 if (customers.get(i).getCustomerId() == c.getCustomerId()) {
 customers.set(i, c);
 found = true;
 break;
 }
 }


 if (found) {
 saveCustomers(customers);
 System.out.println("Customer " + c.getCustomerId() + " updated.");
 } else {
 System.out.println("Customer " + c.getCustomerId() + " not found.");
 }
}


// ── Set discount rate for a customer ─────────────────────────────────────
public void setDiscount(int id, float rate) {
 List<Customer> customers = loadCustomers();
 boolean found = false;


 for (Customer c : customers) {
 if (c.getCustomerId() == id) {
 c.setDiscountRate(rate);
 found = true;
 break;
 }
 }


 if (found) {
 saveCustomers(customers);
 System.out.println("Discount of " + rate + " set for customer " + id + ".");
 } else {
 System.out.println("Customer " + id + " not found.");
 }
}


// ── Ban a customer ────────────────────────────────────────────────────────
public void banCustomer(int id) {
 List<Customer> customers = loadCustomers();
 boolean found = false;


 for (Customer c : customers) {
 if (c.getCustomerId() == id) {
 c.setBanned(true);
 found = true;
 break;
 }
 }


 if (found) {
 saveCustomers(customers);
 System.out.println("Customer " + id + " has been banned.");
 } else {
 System.out.println("Customer " + id + " not found.");
 }
}


// ── List all customers ────────────────────────────────────────────────────
public List<Customer> listCustomers() {
 return loadCustomers();
}


// ── Find a customer by ID ─────────────────────────────────────────────────
public Customer findCustomerById(int id) {
 for (Customer c : loadCustomers()) {
 if (c.getCustomerId() == id) return c;
 }
 return null;
}
}
