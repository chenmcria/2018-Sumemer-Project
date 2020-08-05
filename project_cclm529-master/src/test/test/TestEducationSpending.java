package test;

import exception.InEligibleInputOfLimit;
import exception.NotEnoughLimitLeftException;
import model.Education;
import model.EducationSpending;
import model.Spending;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEducationSpending{
    private EducationSpending testSpending;

    @BeforeEach
    public void setUp() {

        testSpending = new EducationSpending();
        try {
            testSpending.setLimit(5000);
            Spending.amountSpentSoFar =0;
        } catch (InEligibleInputOfLimit inEligibleInputOfLimit) {
            inEligibleInputOfLimit.printStackTrace();
        }
    }

    @Test
    public void testGetAmountSpent() throws NotEnoughLimitLeftException {
        assertEquals(0, testSpending.getAmountSpentSoFar());
        testSpending.addItem(new Education("PHIL220 textbook", 2700));
        assertEquals(2700, testSpending.getAmountSpentSoFar());
    }


    @Test
    public void testAddAmountSpent() throws  NotEnoughLimitLeftException {
        Education education = new Education("GERM101 dictionary", 500);
        testSpending.addItem(education);
        assertEquals(500, testSpending.getAmountSpentSoFar());
        Education education1 = new Education("SPAN101 textbook", 2000);
        testSpending.addItem(education1);
        assertEquals(2500, testSpending.getAmountSpentSoFar());
    }


    @Test
    public void testSetLimit() throws InEligibleInputOfLimit {
        assertEquals(5000, testSpending.getLimit());
        testSpending.setLimit(8000);
        assertEquals(8000, testSpending.getLimit());
    }

    @Test
    public void testGetLimit() {

        assertEquals(5000, testSpending.getLimit());
    }


    @Test
    public void testAddFood() {
        assertEquals(0, testSpending.getItems().size());
        Education education1 = new Education("JAPAN101 textbook", 8900);
        try {
            testSpending.addItem(education1);
        } catch (NotEnoughLimitLeftException e) {
            e.printStackTrace();
        }
        assertEquals(1, testSpending.getItems().size());
    }
}
