import java.io.*;
import java.nio.channels.FileLockInterruptionException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransactionFileManager {
    private static final String FILE_NAME = "transaction.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static void saveTransactions(List<Transaction> transactions) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for(Transaction t : transactions) {
                String line;
                if (t instanceof Expense) {
                    Expense e = (Expense) t;
                    line = String.format(Locale.ENGLISH, "%s,%.2f,%s,%s,%s",
                            e.getType(), e.getAmount(), e.getDate().format(formatter), e.getDescription(), e.getCategory());
                } else {
                    line = String.format(Locale.ENGLISH, "%s,%.2f,%s,%s",
                            t.getType(), t.getAmount(), t.getDate().format(formatter), t.getDescription());
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Failed to save transactions: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        File file = new File(FILE_NAME);
        if(!file.exists()) {
            return transactions; // no file
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",", -1); //include empty fields

                if (parts.length < 4) {
                    System.out.println("⚠️ Skipping malformed line: " + line);
                    continue;
                }

                String type = parts[0];
                double amount = Double.parseDouble(parts[1].replace(",", "."));
                LocalDateTime date = LocalDateTime.parse(parts[2], formatter);
                String description = parts[3];

                if (type.equals("INCOME")) {
                    transactions.add(new Income(amount, date, description));
                } else if (type.equals("EXPENSE") && parts.length >= 5) {
                    ExpenseCategoryEnum category;
                    try {
                        category = ExpenseCategoryEnum.valueOf(parts[4]);
                    } catch (IllegalArgumentException e) {
                        category = ExpenseCategoryEnum.OTHERS; // geçersiz kategori
                    }
                    transactions.add(new Expense(amount, date, description, category));
                } else {
                    System.out.println("⚠️ Unknown transaction type: " + type);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("❌ Failed to load transactions - File Not Found: " + e.getMessage());
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("❌ Failed to load transactions: " + e.getMessage());
        }

        return transactions;
    }
}
