package krzyzowski.tomasz.lab2.lab2;

import org.junit.Test;

import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBMI;
import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBmiMetrical;

import static org.junit.Assert.*;

/**
 * Created by tkrzy on 15.03.2017.
 */

public class CountBmiMetricalTest {

    private CountBMI counterBMI = new CountBmiMetrical();

    @Test
    public void weight_positive_number_is_invalid() {
        float testWeight = 9.0f;

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
        float testWeight = 68;
        float testHeight = 1.7f;
        float actualBMI = 23.53f;

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
        float testHeight = 1.8f;

        assertTrue(counterBMI.isHeightValid(testHeight));
    }

    @Test
    public void height_positive_number_is_invalid_() {
        float testHeight = 0.3f;
        assertFalse(counterBMI.isHeightValid(testHeight));
    }
}
