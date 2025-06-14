import java.time.LocalDateTime;

public class Expense extends Transaction{
    private ExpenseCategoryEnum category;

    public Expense(double amount, LocalDateTime date, String description, ExpenseCategoryEnum category) {
        super(amount, date, description);
        this.category = category;
    }
    @Override
    public String getType() {
        return "EXPENSE";
    }

    public ExpenseCategoryEnum getCategory() {
        return category;
    }
}
