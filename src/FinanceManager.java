import java.util.ArrayList;
import java.util.List;

public class FinanceManager {
    private List<Transaction> transactions;

    public FinanceManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }
}
