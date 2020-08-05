package test;


import exception.InEligibleInputOfLimit;
import exception.NotEnoughLimitLeftException;
import model.Food;
import model.FoodSpending;
import model.Spending;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFoodSpending {
    private FoodSpending testSpending;

    @BeforeEach
    public void setUp() {

        testSpending = new FoodSpending();
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
        testSpending.addItem(new Food("Hot Pot", 1780));
        assertEquals(1780, testSpending.getAmountSpentSoFar());
    }


    @Test
    public void testAddAmountSpent() throws NotEnoughLimitLeftException {
        Food food = new Food("Bubble tea", 500);
        testSpending.addItem(food);
        assertEquals(500, testSpending.getAmountSpentSoFar());
        Food food1 = new Food("Japanese hotpot", 750);
        assertEquals(500, testSpending.getAmountSpentSoFar());
    }


    @Test
    public void testSetLimit() throws InEligibleInputOfLimit {
        assertEquals(2000,testSpending.getLimit());
        testSpending.setLimit(8000);
        assertEquals(8000, testSpending.getLimit());
    }

    @Test
    public void testGetLimit() {
        assertEquals(2000, testSpending.getLimit());
    }


    @Test
    public void testAddFood() {
        assertEquals(0, testSpending.getItems().size());
        Food f1 = new Food("Salmon and Tuna Sashimi", 5000);
        try {
            testSpending.addItem(f1);
        } catch (NotEnoughLimitLeftException e) {
            e.printStackTrace();
        }
    }
}
