package krzyzowski.tomasz.lab2.lab2;

public class Range {

    private float lowerBound;
    private float upperBound;

    public Range(float lowerBound, float upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public boolean contains(float value) {
        return lowerBound <= value && value <= upperBound;
    }

    public float getLowerBound() {
        return lowerBound;
    }

    public float getUpperBound() {
        return upperBound;
    }
}
