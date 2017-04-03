package krzyzowski.tomasz.lab2.lab2.ModelsOfPresentingResult;

/**
 * Created by tkrzy on 29.03.2017.
 */

public class BMIGroupWithColor {
    private int group;
    private int color;

    public BMIGroupWithColor(int group, int color) {
        this.group = group;
        this.color = color;
    }

    public int getGroup() {

        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
