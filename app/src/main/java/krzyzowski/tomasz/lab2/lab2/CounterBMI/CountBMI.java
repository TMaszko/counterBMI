package krzyzowski.tomasz.lab2.lab2.CounterBMI;

import krzyzowski.tomasz.lab2.lab2.*;

/**
 * Created by tkrzy on 27.03.2017.
 */

public abstract class CountBMI {

	private Range weightRange;
	private Range heightRange;

	public CountBMI(Range weightRange, Range heightRange) {
		this.weightRange = weightRange;
		this.heightRange = heightRange;
	}

	public Range getWeightRange() {
		return weightRange;
	}

	public Range getHeightRange() {
		return heightRange;
	}

	public boolean isWeightValid(float weight) {
		return weightRange.contains(weight);
	}

	public boolean isHeightValid(float height) {
		return heightRange.contains(height);
	}

	public float countBmi(float weight, float height) {
		validateWeightAndHeight(weight, height);
		return weight / (height * height);
	}

	private void validateWeightAndHeight(float weight, float height) {
		if (!isWeightValid(weight) || !isHeightValid(height))
			throw new IllegalArgumentException("Niepoprawne argumenty. Waga:" + weight + " Wzrost: " + height);
	}


}
