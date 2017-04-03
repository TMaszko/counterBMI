package krzyzowski.tomasz.lab2.lab2.CounterBMI;

/**
 * Created by tkrzy on 27.03.2017.
 */

public abstract class CountBMI {

    private float minWeightInUnits;  //10.0f;
    private float maxWeightInUnits; //250.0f;
    private float minHeightInUnits; //0.5f;
    private float maxHeightInUnits;               // 2.5f

    public CountBMI(float min_weight, float max_weight, float min_height, float max_height) {
        this.minWeightInUnits = min_weight;
        this.maxWeightInUnits = max_weight;
        this.minHeightInUnits = min_height;
        this.maxHeightInUnits = max_height;
    }


    public boolean isWeightValid(float weight) {
        return weight >= minWeightInUnits && weight <= maxWeightInUnits;
    }

    public boolean isHeightValid(float height) {
        return height >= minHeightInUnits && height <= maxHeightInUnits;
    }

    public boolean areArgsValid(float weight, float height) {
        return isWeightValid(weight) && isHeightValid(height);
    }

    public float countBmi(float weight, float height) {
        if (areArgsValid(weight, height)) {
            float resultBmi = weight / (height * height);
            return resultBmi;
        } else {
            throw new IllegalArgumentException("Niepoprawne argumenty");
        }
    }

}
