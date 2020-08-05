package test;


import model.Food;
import org.junit.jupiter.api.BeforeEach;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestFood extends TestItem {

    @BeforeEach
    public void setUp() {
        testAmount = 163;
        testDescription = "Online Order for Dominos Pizza";
        testCategory = "Food";
        testDate =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());;
        testItem = new Food(testDescription, testAmount);
    }
}
