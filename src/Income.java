import java.time.LocalDateTime;

public class Income extends Transaction{

    public Income(double amount, LocalDateTime date, String description) {
        super(amount, date, description);
    }

    @Override
    public String getType() {
        return "Income";
    }
}
