import java.io.*;
import java.nio.channels.FileLockInterruptionException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileManager {
    private static final String FILE_NAME = "transaction.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private static  void saveTransaction(List<Transaction> transactions) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for(Transaction t : transactions) {
                String line;
                if (t instanceof Expense) {
                    Expense e = (Expense) t;
                    line = String.format("EXPENSE,%.2f,%s%s%s",
                            e.getAmount(), e.getDate().format(formatter), e.getDescription(), e.getCategory());
                } else {
                    line = String.format("INCOME,%.2f,%s,%s,",
                            t.getAmount(), t.getDate().format(formatter), t.getDescription());
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Failed to save transactions: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransaction() {
        List<Transaction> transactions = new ArrayList<>();

        File file = new File(FILE_NAME);
        if(!file.exists()) {
            return transactions; // no data
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1); //include empty fields
                String type = parts[0];
                double amount = Double.parseDouble(parts[1]);
                LocalDateTime date = LocalDateTime.parse(parts[2], formatter);
                String description = parts[3];

                if(type.equals("EXPENSE")) {
                    ExpenseCategoryEnum category = ExpenseCategoryEnum.valueOf(parts[4]);
                    transactions.add(new Expense(amount, date, description, category));
                } else if (type.equals("INCOME")) {
                    transactions.add(new Income(amount, date, description));
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
