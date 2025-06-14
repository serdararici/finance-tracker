import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FinanceManagerTest {

    @Test
    public void testAddIncomeTransaction() {
        FinanceManager manager = new FinanceManager();
        Transaction income = new Income(1000, LocalDateTime.now(), "Salary");

        manager.addTransaction(income);

        assertEquals(1, manager.getAllTransactions().size());
        assertEquals("INCOME", manager.getAllTransactions().get(0).getType());
    }

    @Test
    public void testAddExpenseTransaction() {
        FinanceManager manager = new FinanceManager();
        Transaction expense = new Expense(500, LocalDateTime.now(), "Groceries", ExpenseCategoryEnum.FOOD);

        manager.addTransaction(expense);

        assertEquals(1, manager.getAllTransactions().size());
        assertEquals("EXPENSE", manager.getAllTransactions().get(0).getType());
    }

    @Test
    public void testTotalIncomeAndExpenses() {
        FinanceManager manager = new FinanceManager();
        manager.addTransaction(new Income(1500, LocalDateTime.now(), "Freelance"));
        manager.addTransaction(new Expense(300, LocalDateTime.now(), "Rent", ExpenseCategoryEnum.OTHERS));

        assertEquals(1500, manager.getTotalIncome());
        assertEquals(300, manager.getTotalExpense());
    }

    @Test
    public void testTransactionTypeCheck() {
        Income income = new Income(1200.0, LocalDateTime.now(), "Salary");
        assertEquals("INCOME", income.getType());

        Expense expense = new Expense(200.0, LocalDateTime.now(), "Rent", ExpenseCategoryEnum.RENT);
        assertEquals("EXPENSE", expense.getType());
    }

    @Test
    public void testEmptyTransactionList() {
        FinanceManager manager = new FinanceManager();
        assertTrue(manager.getAllTransactions().isEmpty());
    }
}
