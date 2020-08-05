package test;


import exception.InEligibleInputOfLimit;
import exception.NotEnoughLimitLeftException;
import model.Education;
import model.Food;
import model.Shopping;
import model.Spending;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSpending {
    private Spending testSpending;

    @BeforeEach
    void setUp() throws InEligibleInputOfLimit {

        testSpending = new Spending();
        Spending.LIMIT = 1000;
        Spending.amountSpentSoFar = 0;
    }

    @Test
    void testGetAmountSpent() throws NotEnoughLimitLeftException {
        assertEquals(0, testSpending.getAmountSpentSoFar());
        testSpending.addItem(new Food("Hot Pot", 780));
        assertEquals(780, testSpending.getAmountSpentSoFar());
    }

    @Test
    void testGetLimitLeft() throws NotEnoughLimitLeftException {
        assertEquals(1000, Spending.getLimitLeft());
        Education education = new Education("SPAN101 textbook", 330);
        testSpending.addItem(education);
        assertEquals(670, Spending.getLimitLeft());
        Food food = new Food("Japanese hotpot", 420);
        testSpending.addItem(food);
        assertEquals(250, Spending.getLimitLeft());
    }

    @Test
    void testAddAmountSpent() throws NotEnoughLimitLeftException{
        testSpending.addItem(new Food("Bubble tea", 15));
        assertEquals(15, testSpending.getAmountSpentSoFar());
        testSpending.addItem( new Food("Japanese hotpot", 80));
        assertEquals(95, testSpending.getAmountSpentSoFar());
    }


    @Test
    void testSetLimit() throws InEligibleInputOfLimit {
        assertEquals(1000, testSpending.getLimit());
        testSpending.setLimit(8000);
        assertEquals(8000, testSpending.getLimit());
    }

    @Test
    void testGetLimit()  {
        assertEquals(1000, testSpending.getLimit());
    }


    @Test
    void testAddItem() throws NotEnoughLimitLeftException {
        assertEquals(0, testSpending.getItems().size());
        Food f1 = new Food("Salmon and Tuna Sashimi", 500);
        testSpending.addItem(f1);
        assertEquals(1, testSpending.getItems().size());

    }

    @Test
    void testPrintAllItemsBought() throws NotEnoughLimitLeftException {

        assertEquals(0, testSpending.getItems().size());
        Shopping s1 = new Shopping("Levis t-shirt", 10);
        Shopping s2 = new Shopping("Nike shoes", 20);
        Shopping s3 = new Shopping("Coach handbag", 20);
        testSpending.addItem(s1);
        testSpending.addItem(s2);
        testSpending.addItem(s3);
        assertEquals(3, testSpending.getItems().size());
        Spending.getLimitLeft();
        Spending.printAllItemsBought();
        Spending.getLimitLeft();
        testSpending.getItems();
        testSpending.printItemsBought();
    }


    @Test
    void testsetLimit() throws InEligibleInputOfLimit {
        testSpending.setLimit(200);
        assertEquals(200, 200);
        testSpending.setLimit(500);
        assertEquals(500,500);
        assertEquals(500,testSpending.getLimit());

    }

    @Test
    void testSave() throws IOException, ClassNotFoundException, NotEnoughLimitLeftException {
        testSpending.addItem(new Food("Sandwhich", 20));
        testSpending.addItem(new Shopping("Nike Shoes", 56));
        testSpending.save("savefile.txt");
        FileInputStream fis = new FileInputStream("savefile.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Food food = (Food) ois.readObject();
        ois.close();
    }

    @Test
    void testLoad() throws IOException, ClassNotFoundException, NotEnoughLimitLeftException {
        testSpending.addItem(new Food("Sandwhich", 20));
        testSpending.save("savefile.txt");
        testSpending.load("savefile.txt");
        System.out.println(testSpending.getItems().get(0).toString());
    }

}
