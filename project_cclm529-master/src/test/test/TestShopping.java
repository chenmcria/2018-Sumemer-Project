package test;

import model.Shopping;
import org.junit.jupiter.api.BeforeEach;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestShopping extends TestItem {

    @BeforeEach
    public void setUp() {
        testDescription = "Lululemon leggings";
        testAmount = 460;
        testCategory = "Shopping";
        testDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        testItem = new Shopping(testDescription, testAmount);
    }
}
