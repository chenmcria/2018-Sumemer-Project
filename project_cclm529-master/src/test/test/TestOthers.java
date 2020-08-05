package test;


import model.Others;
import org.junit.jupiter.api.BeforeEach;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestOthers extends TestItem {

    @BeforeEach
    public void setUp() {
        testDescription = "Internet fees for July";
        testAmount = 520;
        testCategory = "Others";
        testDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());;
        testItem = new Others(testDescription, testAmount);

    }
}