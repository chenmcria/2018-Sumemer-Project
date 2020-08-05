package test;


import model.HealthCare;
import org.junit.jupiter.api.BeforeEach;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestHealthCare extends TestItem {

    @BeforeEach
    public void setUp() {
        testDescription = "Tooth extraction";
        testAmount = 27000;
        testCategory = "HealthCare";
        testDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        testItem = new HealthCare(testDescription,testAmount);
    }
}