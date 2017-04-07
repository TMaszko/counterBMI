package krzyzowski.tomasz.lab2.lab2.bmi;

import java.util.Arrays;
import java.util.List;

import krzyzowski.tomasz.lab2.lab2.R;
import krzyzowski.tomasz.lab2.lab2.Range;
import krzyzowski.tomasz.lab2.lab2.bmi.BMIRange;

public class BMIRanges {

    private static List<BMIRange> ranges = Arrays.asList(
            new BMIRange(R.string.description_underweight, R.color.colorUnderweight, new Range(0, 18.5f)),
            new BMIRange(R.string.description_normal_weight, R.color.colorNormalWeight, new Range(18.5f, 25f)),
            new BMIRange(R.string.description_overweight, R.color.colorOverweight, new Range(25f, 30f)),
            new BMIRange(R.string.description_obese_weight, R.color.colorObeseWeight, new Range(30f, Float.POSITIVE_INFINITY))
    );

    public static BMIRange getRange(float value) {
        BMIRange range = null;
        for(int i = 0; i < ranges.size() && range == null; ++i) {
            BMIRange examinedRange = ranges.get(i);
            range = examinedRange.isInRange(value) ? examinedRange : null;
        }
        return range;
    }

}
