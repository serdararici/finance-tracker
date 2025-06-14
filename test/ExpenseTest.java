import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTest {

    @Test
    public void testExpenseCreation() {
        Expense expense = new Expense(300, LocalDateTime.now(), "Dinner", ExpenseCategoryEnum.FOOD);
        assertEquals(300, expense.getAmount());
        assertEquals("Dinner", expense.getDescription());
        assertEquals(ExpenseCategoryEnum.FOOD, expense.getCategory());
        assertEquals("EXPENSE", expense.getType());
    }
}
