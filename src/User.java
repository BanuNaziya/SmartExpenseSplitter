import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
    private String name;
    private Map<String, Double> balanceSheet = new HashMap<>();

    public User(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public void addExpense(String otherUser, double amount) {
        balanceSheet.put(otherUser, balanceSheet.getOrDefault(otherUser, 0.0) + amount);
    }

    public Map<String, Double> getBalanceSheet() {
        return balanceSheet;
    }

    @Override
    public String toString() {
        return name;
    }
}
