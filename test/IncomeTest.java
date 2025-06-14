import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class IncomeTest {

    @Test
    public void testIncomeCreation() {
        Income income = new Income(200, LocalDateTime.now(), "Test Income");
        assertEquals(200, income.getAmount());
        assertEquals("Test Income", income.getDescription());
        assertEquals("INCOME", income.getType());
    }
}
