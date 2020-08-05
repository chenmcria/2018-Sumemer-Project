package test;

import exception.InEligibleInputOfLimit;
import exception.NotEnoughLimitLeftException;
import model.HealthCare;
import model.HealthCareSpending;
import model.Spending;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestHealthCareSpending {
    private HealthCareSpending testSpending;

    @BeforeEach
    public void setUp() {

        testSpending = new HealthCareSpending();

        try {
            testSpending.setLimit(4000);
            Spending.amountSpentSoFar = 0;
        } catch (InEligibleInputOfLimit inEligibleInputOfLimit) {
            inEligibleInputOfLimit.printStackTrace();
        }
    }

    @Test
    public void testGetAmountSpent() throws NotEnoughLimitLeftException {
        assertEquals(0, testSpending.getAmountSpentSoFar());
        testSpending.addItem(new HealthCare("Medical examination", 680));
        assertEquals(680, testSpending.getAmountSpentSoFar());
        testSpending.addItem(new HealthCare("Eye care", 960));
        assertEquals(1640, testSpending.getAmountSpentSoFar());
    }


    @Test
    public void testAddAmountSpent() throws NotEnoughLimitLeftException {
        HealthCare healthCare = new HealthCare ("Healthcare products from Costo", 340);
        testSpending.addItem(healthCare);
        assertEquals(340, testSpending.getAmountSpentSoFar());
        assertTrue(testSpending.addAmountSpent(healthCare.getAmount()));
        HealthCare healthCare1 = new HealthCare("Had a wisdom tooth extracted", 4000);
        assertFalse(testSpending.addAmountSpent(healthCare1.getAmount()));
    }


    @Test
    public void testSetLimit() throws InEligibleInputOfLimit {
        assertEquals(4000,testSpending.getLimit());
        testSpending.setLimit(5000);
        assertEquals(5000, testSpending.getLimit());
    }

    @Test
    public void testGetLimit() {
        assertEquals(4000, testSpending.getLimit());
    }


    @Test
    public void testAddFood() {
        assertEquals(0, testSpending.getItems().size());
        HealthCare healthCare = new HealthCare("Fix teeth", 6000);
        try {
            testSpending.addItem(healthCare);
        } catch (NotEnoughLimitLeftException e) {
            e.printStackTrace();
        }
    }
}
