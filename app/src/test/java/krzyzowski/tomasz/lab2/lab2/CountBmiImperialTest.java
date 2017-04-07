package krzyzowski.tomasz.lab2.lab2;

import org.junit.Test;

import krzyzowski.tomasz.lab2.lab2.bmi.BMICalculator;
import krzyzowski.tomasz.lab2.lab2.bmi.BMICalculatorImperial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by tkrzy on 27.03.2017.
 */

public class CountBmiImperialTest {

    private BMICalculator counterBMI = new BMICalculatorImperial();

    @Test
    public void weight_positive_number_is_invalid() {
        float testWeight = 20.0f; //9kg

        assertFalse(counterBMI.isWeightValid(testWeight));
    }

    @Test
    public void weight_under_zero_is_invalid() {
        float testWeight = -1.0f;
        boolean actual = counterBMI.isWeightValid(testWeight);

        assertFalse(actual);
    }

    @Test
    public void is_valid_bmi() {
        float testWeight = 149.91f; //68kg
        float testHeight = 5.57f;  //1.7m
        float actualBMI = 23.58f; // actualBmi

        assertEquals(actualBMI, counterBMI.countBmi(testWeight, testHeight), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isThrowingIllegalArgumentException() {

        float testWeight = -68f;
        float testHeight = 1.7f;

        float actualBMI = counterBMI.countBmi(testWeight, testHeight);
    }

    @Test
    public void height_under_zero_is_invalid() {
        float testHeight = -1.7f;
        boolean actual = counterBMI.isHeightValid(testHeight);

        assertFalse(actual);
    }

    @Test
    public void height_is_valid() {
        float testHeight = 5.90f; //1.8m

        assertTrue(counterBMI.isHeightValid(testHeight));
    }

    @Test
    public void height_positive_number_is_invalid_() {
        float testHeight = 0.98f;//0.3m
        assertFalse(counterBMI.isHeightValid(testHeight));
    }
}
