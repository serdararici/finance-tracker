import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinanceManager manager = new FinanceManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        boolean running = true;

        while(running) {
            System.out.println("\n===== Personal Finance Tracker =====");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume new line

            switch (choice) {
                case 1:
                    handleAddIncome(scanner, manager);
                    break;

                case 2:
                    handleAddExpense(scanner, manager);
                    break;

                case 3:
                    handleViewTransactions(manager, formatter);
                    break;

                case 0:
                    running = false;
                    System.out.println("👋 Goodbye!");
                    break;

                default:
                    System.out.println("❗ Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    public static void handleAddIncome(Scanner scanner, FinanceManager manager) {
        double incomeAmount = readAmount(scanner);
        scanner.nextLine();

        System.out.print("Enter description: ");
        String incomeDesc = scanner.nextLine().trim();
        if (incomeDesc.isEmpty()) {
            incomeDesc = "No description";
        }

        Income income = new Income(incomeAmount, LocalDateTime.now(), incomeDesc);
        manager.addTransaction(income);
        System.out.println("✅ Income added.");
    }

    public static void handleAddExpense(Scanner scanner, FinanceManager manager) {
        double expenseAmount = readAmount(scanner);
        scanner.nextLine();
        System.out.println("Enter description: ");
        String expenseDesc = scanner.nextLine();

        System.out.println("Choose category: ");
        for(ExpenseCategoryEnum category : ExpenseCategoryEnum.values()) {
            System.out.println("- " + category);
        }
        String categoryInput = scanner.nextLine().toUpperCase();
        ExpenseCategoryEnum categoryEnum;
        try {
            categoryEnum = ExpenseCategoryEnum.valueOf(categoryInput);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid category. Defaulting to OTHERS.");
            categoryEnum = ExpenseCategoryEnum.OTHERS;
        }

        Expense expense = new Expense(expenseAmount, LocalDateTime.now(), expenseDesc, categoryEnum);
        manager.addTransaction(expense);
        System.out.println("✅ Expense added.");
    }

    public static void handleViewTransactions(FinanceManager manager, DateTimeFormatter formatter) {
        System.out.println("=== All Transactions ===");

        if (manager.getAllTransactions().isEmpty()) {
            System.out.println("❌ No transactions found! Add some income or expenses to get started. ❌");
            return;
        }

        for (Transaction t : manager.getAllTransactions()) {
            String formattedDate = t.getDate().format(formatter);
            System.out.println(t.getType() + " | " + t.getAmount() + "₺ | " + formattedDate + " | " + t.getDescription());

            if (t instanceof Expense) {
                System.out.println("   Category: " + ((Expense) t).getCategory());
            }
        }
    }

    public static double readAmount(Scanner scanner) {
        double value = -1;
        while (value <= 0) {
            System.out.print("Enter amount: ");
            try {
                value = Double.parseDouble(scanner.next());
                if (value <= 0) {
                    System.out.println("❗ Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a numeric value.");
            }
        }
        return value;
    }


}
