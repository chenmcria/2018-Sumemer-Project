package test;


import exception.InEligibleInputOfLimit;
import exception.NotEnoughLimitLeftException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestException {
    Item healthCare;
    Item shopping;
    Spending testSpending;

    @BeforeEach
    void setUp() {
        healthCare = new HealthCare("Physical examination", 3400);
        shopping = new Shopping("Supreme hoodie", 1700);
        testSpending = new Spending();
    }

    @Test
    void testEligibleInputOfLimit() {
        try {
            testSpending.setLimit(200);
        } catch (InEligibleInputOfLimit e) {
            fail("Unexpected exception");
        }
    }


    @Test
    void testInEligibleInputOfLimit() {
        try{
            testSpending.setLimit(-500);
            fail("InEligibleInputOfLimit should be thrown");
        } catch (InEligibleInputOfLimit e) {
            System.out.println("Thrown as expected");
        }
    }

    @Test
    void testEnoughLimitLeft() {
        try {
            testSpending.setLimit(30000);
            testSpending.addItem(healthCare);
        } catch (NotEnoughLimitLeftException e) {
            fail("Unexpected exception");
        } catch (InEligibleInputOfLimit e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void testNotEnoughLimitLeft() {
        try {
            testSpending.setLimit(200);
            testSpending.addItem(healthCare);
            fail("NotEnoughLimitLeft should be thrown");
        } catch (InEligibleInputOfLimit e) {
            fail("Unexpected exception");
        } catch (NotEnoughLimitLeftException e) {
            System.out.println("expected");
        }
    }

    @Test
    void testInEligibleInputOfLimitAndNotEnoughLimitLeft() {
        try{
            testSpending.setLimit(-200);
            testSpending.addItem(shopping);
            fail("InEligibleInputOfLimit should be thrown");
        } catch (InEligibleInputOfLimit e) {
            System.out.println("exception expected");
        } catch (NotEnoughLimitLeftException e) {
            fail("Unexpected exception");
        }

        try{
            testSpending.setLimit(50000);
            testSpending.addItem(shopping);
            testSpending.addItem(healthCare);
        } catch(InEligibleInputOfLimit e) {
            fail("Unexpected exception");
        } catch(NotEnoughLimitLeftException e) {
            fail("Unexpected exception");
        }
    }
}
