package krzyzowski.tomasz.lab2.lab2.CounterBMI;

/**
 * Created by tkrzy on 15.03.2017.
 */

public class CountBmiMetrical extends CountBMI {
    public static final float MIN_WEIGHT = 10f;
    public static final float MAX_WEIGHT = 250.0f;
    public static final float MIN_HEIGHT = 0.5f;
    public static final float MAX_HEIGHT = 2.5f;

    public CountBmiMetrical() {
        super(MIN_WEIGHT, MAX_WEIGHT, MIN_HEIGHT, MAX_HEIGHT);
    }
}
