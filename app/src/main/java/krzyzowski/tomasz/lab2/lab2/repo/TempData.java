package krzyzowski.tomasz.lab2.lab2.repo;

import krzyzowski.tomasz.lab2.lab2.Units;

public class TempData {

    private Float weight;
    private Float height;
    private Float result;
    private Units units;

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    public void setUnits(Units units) {
        this.units = units;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public float getResult() {
        return result;
    }

    public Units getUnits() {
        return units;
    }

    public boolean hasWeight() {
        return weight != null;
    }

    public boolean hasHeight() {
        return height != null;
    }

    public boolean hasResult() {
        return result != null;
    }

    public boolean hasUnits() {
        return units != null;
    }

}
