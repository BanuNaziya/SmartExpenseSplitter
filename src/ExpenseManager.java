import java.io.*;
import java.util.*;

public class ExpenseManager {
    private Map<String, User> users = new HashMap<>();
    private final String fileName = "users.ser";

    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            users = (Map<String, User>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(users);
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void addUser(String name) {
        if (!users.containsKey(name)) {
            users.put(name, new User(name));
        }
    }

    public void splitExpense(String paidBy, double amount, List<String> participants) {
        double splitAmount = amount / participants.size();
        for (String user : participants) {
            if (!user.equals(paidBy)) {
                users.get(user).addExpense(paidBy, splitAmount);
                users.get(paidBy).addExpense(user, -splitAmount);
            }
        }
    }

    public void showBalances() {
        for (User user : users.values()) {
            for (Map.Entry<String, Double> entry : user.getBalanceSheet().entrySet()) {
                if (entry.getValue() > 0) {
                    System.out.println(user.getName() + " owes " + entry.getKey() + ": ₹" + entry.getValue());
                }
            }
        }
    }
}
