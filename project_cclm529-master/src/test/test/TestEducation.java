package test;


import model.Education;
import org.junit.jupiter.api.BeforeEach;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestEducation extends TestItem {

    @BeforeEach
    void setUp() {
        testDescription = "BIOL121 textbook";
        testAmount = 1150;
        testDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        testCategory = "Education";
        testItem = new Education(testDescription, testAmount);
    }


}