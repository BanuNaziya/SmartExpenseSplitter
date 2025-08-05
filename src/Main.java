import java.util.*;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner sc = new Scanner(System.in);
        manager.loadData();

        while (true) {
            System.out.println("\n1. Add User\n2. Add Expense\n3. Show Balances\n4. Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    manager.addUser(name);
                    break;
                case 2:
                    System.out.print("Who paid? ");
                    String paidBy = sc.nextLine();
                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter participant names (comma separated): ");
                    String[] names = sc.nextLine().split(",");
                    List<String> participants = Arrays.asList(names);
                    manager.splitExpense(paidBy, amount, participants);
                    break;
                case 3:
                    manager.showBalances();
                    break;
                case 4:
                    manager.saveData();
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}
