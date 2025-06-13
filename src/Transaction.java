import java.time.LocalDateTime;

public abstract class Transaction {
    private double amount;
    private LocalDateTime date;
    private String description;

    public Transaction(double amount, LocalDateTime date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }


    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    // Abstract method to get transaction type
    public abstract String getType();
}
