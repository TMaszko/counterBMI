package krzyzowski.tomasz.lab2.lab2.CounterBMI;


/**
 * Created by tkrzy on 27.03.2017.
 */

public class CountBmiImperial extends CountBMI {
    public static float ratioFeetsToMeters = 3.2808399f;
    public static float ratioPoundsToKg = 2.20462262f;

    public static final float MIN_WEIGHT = CountBmiMetrical.MIN_WEIGHT * ratioPoundsToKg;
    public static final float MAX_WEIGHT = CountBmiMetrical.MAX_WEIGHT * ratioPoundsToKg;
    public static final float MIN_HEIGHT = CountBmiMetrical.MIN_HEIGHT * ratioFeetsToMeters;
    public static final float MAX_HEIGHT = CountBmiMetrical.MAX_HEIGHT * ratioFeetsToMeters;

    public CountBmiImperial() {
        super((MIN_WEIGHT), MAX_WEIGHT, MIN_HEIGHT, MAX_HEIGHT);
    }

    @Override
    public float countBmi(float weight, float height) {
        return super.countBmi(weight, height)/144 * 703;
    }
}
