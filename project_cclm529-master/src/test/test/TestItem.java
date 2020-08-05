package test;


import model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class TestItem {
    protected String testDescription;
    protected int testAmount;
    protected String testDate;
    protected Item testItem;
    protected String testCategory;

    @Test
    void testConstructor() {
        assertEquals(testDescription, testItem.getDescription());
        assertEquals(testAmount, testItem.getAmount());
        assertEquals(testDate, testItem.getDateString());
        assertEquals(testCategory, testItem.getCategory());
    }

    @Test
    void testSetDescription() {
        assertEquals(testDescription, testItem.getDescription());
        testItem.setDescription("Gucci belt");
        assertEquals("Gucci belt", testItem.getDescription());
    }

    @Test
    void testSetDataString() {
        assertEquals(testDate, testItem.getDateString());
        testItem.setDateString("2019-07-20");
        assertEquals("2019-07-20", testItem.getDateString());
    }

    @Test
    void testSetAmount() {
        assertEquals(testAmount, testItem.getAmount());
        testItem.setAmount(50000);
        assertEquals(50000, testItem.getAmount());
    }
//
//    @Test
//    void testSetCategory() {
//        assertEquals(testCategory, testItem.getCategory());
//        testItem.setCategory("Education");
//        assertEquals("Education", testItem.getCategory());
//    }
    @Test
    void testGetCategory() {
        assertEquals(testCategory, testItem.getCategory());
    }
    @Test
    void testToString() {
        System.out.println(testItem.toString());
    }

    @Test
    void testGetAmount() {
        assertEquals(testAmount, testItem.getAmount());
    }

    @Test
    void testGetDateString() {
        assertEquals(testDate,testItem.getDateString());
    }

    @Test
    void testGetDescription() {
        assertEquals(testDescription, testItem.getDescription());
    }
}