package krzyzowski.tomasz.lab2.lab2.CounterBMI;

import krzyzowski.tomasz.lab2.lab2.*;

public class CountBmiImperial extends CountBMI {

    private static final float MIN_WEIGHT = 22.05f;
    private static final float MAX_WEIGHT = 551.16f;
    private static final float MIN_HEIGHT = 1.64f;
    private static final float MAX_HEIGHT = 8.20f;

    public CountBmiImperial() {
        super(
                new Range(MIN_WEIGHT, MAX_WEIGHT),
                new Range(MIN_HEIGHT, MAX_HEIGHT)
        );
    }

    @Override
    public float countBmi(float weight, float height) {
        return super.countBmi(weight, height)/144 * 703;
    }

}
