package krzyzowski.tomasz.lab2.lab2.bmi;

import krzyzowski.tomasz.lab2.lab2.Range;


public class BMIRange {

    private int textId;
    private int colorId;
    private Range range;

    BMIRange(int textId, int colorId, Range range) {
        this.textId = textId;
        this.colorId = colorId;
        this.range = range;
    }

    public int getTextId() {
        return textId;
    }

    public int getColorId() {
        return colorId;
    }

    boolean isInRange(float value) {
        return range.contains(value);
    }
}
