package test;

import exception.InEligibleInputOfLimit;
import exception.NotEnoughLimitLeftException;
import model.Others;
import model.OthersSpending;
import model.Spending;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestOthersSpending {
    private OthersSpending testSpending;

    @BeforeEach
    public void setUp() {

        testSpending = new OthersSpending();
        try {
            testSpending.setLimit(1000);
            Spending.amountSpentSoFar = 0;
        } catch (InEligibleInputOfLimit inEligibleInputOfLimit) {
            inEligibleInputOfLimit.printStackTrace();
        }
    }

    @Test
    public void testGetAmountSpent() throws NotEnoughLimitLeftException {
        assertEquals(0, testSpending.getAmountSpentSoFar());
        testSpending.addItem(new Others("PNE day-pass", 80));
        assertEquals(80, testSpending.getAmountSpentSoFar());
        testSpending.addItem(new Others("Richmond night market ticket", 30));
        assertEquals(110, testSpending.getAmountSpentSoFar());
    }


    @Test
    public void testAddAmountSpent() throws NotEnoughLimitLeftException {
        Others others = new Others("Chilliwack bluberry festival ticket", 30);
        testSpending.addItem(others);
        assertEquals(30, testSpending.getAmountSpentSoFar());
        assertTrue(testSpending.addAmountSpent(others.getAmount()));
        Others others1 = new Others("Ikea furniture for new house", 980);
        assertFalse(testSpending.addAmountSpent(others1.getAmount()));
    }


    @Test
    public void testSetLimit() throws InEligibleInputOfLimit {
        assertEquals(1000, testSpending.getLimit());
        testSpending.setLimit(900);
        assertEquals(900, testSpending.getLimit());
    }

    @Test
    public void testGetLimit() {
        assertEquals(1000, testSpending.getLimit());
    }


    @Test
    public void testAddOthers() throws NotEnoughLimitLeftException {
        assertEquals(0, testSpending.getItems().size());
        Others others = new Others("Residence fee for August", 800);
        testSpending.addItem(others);
    }
}
