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

    public double getTotalIncome() {
        double total = 0;
        for(Transaction t : transactions) {
            if(t instanceof Income) {
                total += t.getAmount();
            }
        }
        return  total;
    }

    public double getTotalExpense() {
        double total = 0;
        for(Transaction t : transactions) {
            if(t instanceof Expense) {
                total += t.getAmount();
            }
        }
        return total;
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }

}
