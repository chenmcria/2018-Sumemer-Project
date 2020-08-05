package test;

import exception.InEligibleInputOfLimit;
import exception.NotEnoughLimitLeftException;
import model.Shopping;
import model.ShoppingSpending;
import model.Spending;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestShoppingSpending {
    private ShoppingSpending testSpending;

    @BeforeEach
    public void setUp() {
        testSpending = new ShoppingSpending();
        try {
            testSpending.setLimit(2000);
            Spending.amountSpentSoFar =0;
        } catch (InEligibleInputOfLimit inEligibleInputOfLimit) {
            inEligibleInputOfLimit.printStackTrace();
        }
    }

    @Test
    public void testGetAmountSpent() throws NotEnoughLimitLeftException {
        assertEquals(0, testSpending.getAmountSpentSoFar());
        testSpending.addItem(new Shopping("Fila sneaker", 170));
        assertEquals(170, testSpending.getAmountSpentSoFar());
        testSpending.addItem((new Shopping("Puma socks", 80)));
        assertEquals(250, testSpending.getAmountSpentSoFar());
    }


    @Test
    public void testAddAmountSpent() throws NotEnoughLimitLeftException {
        Shopping shopping = new Shopping("Balenciaga sneaker", 2100);
        assertFalse(testSpending.addAmountSpent(shopping.getAmount()));
        assertEquals(2100, testSpending.getAmountSpentSoFar());
    }


    @Test
    public void testSetLimit() throws InEligibleInputOfLimit {
        assertEquals(2000, testSpending.getLimit());
        testSpending.setLimit(8000);
        assertEquals(8000, testSpending.getLimit());
    }

    @Test
    public void testGetLimit() {
        assertEquals(2000, testSpending.getLimit());
    }


    @Test
    public void testAddShopping() {
        assertEquals(0, testSpending.getItems().size());
        Shopping shopping= new Shopping("Prada wallet", 8900);
        try {
            testSpending.addItem(shopping);
        } catch (NotEnoughLimitLeftException e) {
            e.printStackTrace();
        }
        assertEquals(1, testSpending.getItems().size());
    }
}
