import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CashRegisterTest {
    CashRegister r;

    @BeforeAll
    void init() {
        r = new CashRegister(new CashRegister.Cash(1, 1, 2, 5, 1));
    }

    @Test
    @Order(1)
    void when_add_should_return_added_denomination() {
        r.add(new CashRegister.Cash(1, 0, 0, 0, 0));
        CashRegister.Cash cash = r.getCash();
        assertEquals(cash.getNumberOf20(), 2);
        assertEquals(cash.getNumberOf10(), 1);
        assertEquals(cash.getNumberOf5(), 2);
        assertEquals(cash.getNumberOf2(), 5);
        assertEquals(cash.getNumberOf1(), 1);
    }

    @Test
    @Order(2)
    void when_get_changes_should_return_right_denomination1() {
        CashRegister.Cash changes = r.getChanges(2, new CashRegister.Cash(0, 1, 0, 0, 0));
        assertEquals(changes.getNumberOf20(), 0);
        assertEquals(changes.getNumberOf10(), 0);
        assertEquals(changes.getNumberOf5(), 1);
        assertEquals(changes.getNumberOf2(), 1);
        assertEquals(changes.getNumberOf1(), 1);

        CashRegister.Cash availableCash = r.getCash();
        assertEquals(availableCash.getNumberOf20(), 2);
        assertEquals(availableCash.getNumberOf10(), 2);
        assertEquals(availableCash.getNumberOf5(), 1);
        assertEquals(availableCash.getNumberOf2(), 4);
        assertEquals(availableCash.getNumberOf1(), 0);
    }

    @Test
    @Order(3)
    void when_get_changes_should_return_right_denomination2() {
        CashRegister.Cash changes = r.getChanges(2, new CashRegister.Cash(0, 1, 0, 0, 0));
        assertEquals(changes.getNumberOf20(), 0);
        assertEquals(changes.getNumberOf10(), 0);
        assertEquals(changes.getNumberOf5(), 0);
        assertEquals(changes.getNumberOf2(), 4);
        assertEquals(changes.getNumberOf1(), 0);

        CashRegister.Cash availableCash = r.getCash();
        assertEquals(availableCash.getNumberOf20(), 2);
        assertEquals(availableCash.getNumberOf10(), 3);
        assertEquals(availableCash.getNumberOf5(), 1);
        assertEquals(availableCash.getNumberOf2(), 0);
        assertEquals(availableCash.getNumberOf1(), 0);
    }

    @Test
    @Order(4)
    void when_get_changes_should_return_error_and_rollback_must_happen() {
        assertThrows(IllegalStateException.class, () -> r.getChanges(1, new CashRegister.Cash(1, 0, 0, 0, 0)));
        CashRegister.Cash availableCash = r.getCash();
        assertEquals(availableCash.getNumberOf20(), 2);
        assertEquals(availableCash.getNumberOf10(), 3);
        assertEquals(availableCash.getNumberOf5(), 1);
        assertEquals(availableCash.getNumberOf2(), 0);
        assertEquals(availableCash.getNumberOf1(), 0);
    }

    @Test
    @Order(5)
    void when_get_changes_should_return_right_denomination3() {
        CashRegister.Cash changes = r.getChanges(5, new CashRegister.Cash(0, 3, 0, 0, 0));
        assertEquals(changes.getNumberOf20(), 1);
        assertEquals(changes.getNumberOf10(), 0);
        assertEquals(changes.getNumberOf5(), 1);
        assertEquals(changes.getNumberOf2(), 0);
        assertEquals(changes.getNumberOf1(), 0);

        CashRegister.Cash availableCash = r.getCash();
        assertEquals(availableCash.getNumberOf20(), 1);
        assertEquals(availableCash.getNumberOf10(), 6);
        assertEquals(availableCash.getNumberOf5(), 0);
        assertEquals(availableCash.getNumberOf2(), 0);
        assertEquals(availableCash.getNumberOf1(), 0);
    }

    @Test
    @Order(6)
    void when_get_changes_should_return_right_denomination4() {
        CashRegister.Cash changes = r.getChanges(4, new CashRegister.Cash(0, 4, 0, 4, 0));
        assertEquals(changes.getNumberOf20(), 1);
        assertEquals(changes.getNumberOf10(), 2);
        assertEquals(changes.getNumberOf5(), 0);
        assertEquals(changes.getNumberOf2(), 2);
        assertEquals(changes.getNumberOf1(), 0);

        CashRegister.Cash availableCash = r.getCash();
        assertEquals(availableCash.getNumberOf20(), 0);
        assertEquals(availableCash.getNumberOf10(), 8);
        assertEquals(availableCash.getNumberOf5(), 0);
        assertEquals(availableCash.getNumberOf2(), 2);
        assertEquals(availableCash.getNumberOf1(), 0);
    }

    @Test
    void when_provided_return_amount_get_all_possible_available_denominations() {
        int[] denominations = {20, 10, 5, 2, 1}; //Todo: hardcoded denominations. To be replaced.
        int[] vals = new int[denominations.length];
        var possibleCombinations = new ArrayList<CashRegister.Cash>();
        int returnAmount = 21;
        var availableCash = new CashRegister.Cash(2, 2, 2, 2, 2);
        CashRegister.getAllCombinations(0, denominations, vals, returnAmount, possibleCombinations, availableCash);
        assertEquals(possibleCombinations.size(), 4);
        assertTrue(possibleCombinations.stream().anyMatch((cash) -> cash.equals(new CashRegister.Cash(1, 0, 0, 0, 1))));
        assertTrue(possibleCombinations.stream().anyMatch((cash) -> cash.equals(new CashRegister.Cash(0, 1, 1, 2, 2))));
        assertTrue(possibleCombinations.stream().anyMatch((cash) -> cash.equals(new CashRegister.Cash(0, 1, 2, 0, 1))));
        assertTrue(possibleCombinations.stream().anyMatch((cash) -> cash.equals(new CashRegister.Cash(0, 2, 0, 0, 1))));
    }

}