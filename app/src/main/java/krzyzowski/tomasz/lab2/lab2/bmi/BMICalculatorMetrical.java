package krzyzowski.tomasz.lab2.lab2.bmi;

import krzyzowski.tomasz.lab2.lab2.*;

/**
 * Created by tkrzy on 15.03.2017.
 */

public class BMICalculatorMetrical extends BMICalculator {

	public static final float MIN_WEIGHT = 10f;
	public static final float MAX_WEIGHT = 250.0f;
	public static final float MIN_HEIGHT = 0.5f;
	public static final float MAX_HEIGHT = 2.5f;

	public BMICalculatorMetrical() {
		super(
				new Range(MIN_WEIGHT, MAX_WEIGHT),
				new Range(MIN_HEIGHT, MAX_HEIGHT)
		);
	}
}
