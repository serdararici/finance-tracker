import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FinanceManager manager = new FinanceManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        boolean running = true;

        List<Transaction> loaded = TransactionFileManager.loadTransactions();
        loaded.forEach(manager::addTransaction);

        while(running) {
            printMenu();

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
                case 4:
                    handleSummary(manager);
                    break;
                case 5:
                    handleFilterTransactions(scanner, manager, formatter);
                    break;
                case 6:
                    //handleDeleteTransaction(scanner, manager);
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
        TransactionFileManager.saveTransactions(manager.getAllTransactions());

    }

    public static void handleAddExpense(Scanner scanner, FinanceManager manager) {
        double expenseAmount = readAmount(scanner);
        scanner.nextLine();
        System.out.print("Enter description: ");
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
        TransactionFileManager.saveTransactions(manager.getAllTransactions());
    }

    public static void handleViewTransactions(FinanceManager manager, DateTimeFormatter formatter) {
        System.out.println("=== All Transactions ===");

        if (manager.getAllTransactions().isEmpty()) {
            System.out.println("❌ No transactions found! Add some income or expenses to get started. ❌");
            return;
        }

        for (Transaction t : manager.getAllTransactions()) {
            String formattedDate = t.getDate().format(formatter);

            if (t instanceof Expense) {
                System.out.println(t.getType() + " | " + t.getAmount() + "₺ | " + formattedDate + " | " + t.getDescription() + " | Category: " + ((Expense) t).getCategory());
            } else {
                System.out.println(t.getType() + " | " + t.getAmount() + "₺ | " + formattedDate + " | " + t.getDescription());
            }
        }
    }

    public static double readAmount(Scanner scanner) {
        double value = -1;
        while (value <= 0) {
            System.out.print("Enter amount: ");
            String input = scanner.next();

            try {
                value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("❗ Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a numeric value.");
            }
        }
        return value;
    }


    public static void printMenu() {
        System.out.println("\n===== Personal Finance Tracker =====");
        System.out.println("1. Add Income");
        System.out.println("2. Add Expense");
        System.out.println("3. View All Transactions");
        System.out.println("4. View Summary");
        System.out.println("5. Filter Transactions");
        System.out.println("6. Delete a Transaction");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    public static void handleSummary(FinanceManager manager) {
        System.out.println("\n=== Financial Summary ===");

        double income = manager.getTotalIncome();
        double expense = manager.getTotalExpense();
        double balance = manager.getBalance();

        System.out.printf("Total Income:  %.2f₺\n", income);
        System.out.printf("Total Expense: %.2f₺\n", expense);
        System.out.printf("Balance:       %.2f₺\n", balance);
        System.out.println("----------------------------------");

        if (balance > 0) {
            System.out.println("🟢 You are in surplus.");
        } else if (balance < 0) {
            System.out.println("🔴 You are in deficit.");
        } else {
            System.out.println("🟡 Your balance is neutral.");
        }
    }

    public static void handleFilterTransactions(Scanner scanner, FinanceManager manager, DateTimeFormatter formatter) {
        System.out.println("\n=== Filter Transactions ===");
        System.out.println("Filter by:");
        System.out.println("1. Date");
        System.out.println("2. Type (INCOME/EXPENSE)");
        System.out.println("3. Expense Category");

        System.out.println("Choose filter type: ");
        String choice = scanner.nextLine().trim();

        List<Transaction> filtered = new ArrayList<>();

        switch (choice) {
            case "1":
                System.out.print("Enter date (dd-MM-yyyy): ");
                String dateInput = scanner.nextLine();
                try {
                    DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate filterDate = LocalDate.parse(dateInput, customFormat);
                    for (Transaction t : manager.getAllTransactions()) {
                        if(t.getDate().toLocalDate().equals(filterDate)) {
                            filtered.add(t);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("❌ Invalid date format.");
                    return;
                }
                break;

            case "2":
                System.out.print("Enter type (INCOME/EXPENSE): ");
                String typeInput = scanner.nextLine().toUpperCase();
                for (Transaction t : manager.getAllTransactions()) {
                    if (t.getType().equalsIgnoreCase(typeInput)) {
                        filtered.add(t);
                    }
                }
                break;

            case "3":
                System.out.print("Enter category (e.g. FOOD, BILLS): ");
                String categoryInput = scanner.nextLine().toUpperCase();
                for (Transaction t : manager.getAllTransactions()) {
                    if (t instanceof Expense) {
                        Expense e = (Expense) t;
                        if (e.getCategory().toString().equalsIgnoreCase(categoryInput)) {
                            filtered.add(t);
                        }
                    }
                }
                break;

            default:
                System.out.println("❌ Invalid option.");
                return;
        }

        // Filtered results
        if (filtered.isEmpty()) {
            System.out.println("❗ No transactions found with this filter.");
        } else {
            System.out.println("\n--- Filtered Transactions ---");
            for (Transaction t : filtered) {
                String formattedDate = t.getDate().format(formatter);

                if (t instanceof Expense) {
                    System.out.println(t.getType() + " | " + t.getAmount() + "₺ | " + formattedDate + " | " + t.getDescription() + " | Category: " + ((Expense) t).getCategory());
                } else {
                    System.out.println(t.getType() + " | " + t.getAmount() + "₺ | " + formattedDate + " | " + t.getDescription());
                }
            }
        }
    }

}
