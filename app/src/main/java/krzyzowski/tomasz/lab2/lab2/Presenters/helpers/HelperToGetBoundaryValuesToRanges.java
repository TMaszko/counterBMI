package krzyzowski.tomasz.lab2.lab2.Presenters.helpers;

import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBmiMetrical;
import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBmiImperial;

/**
 * Created by tkrzy on 29.03.2017.
 */

public class HelperToGetBoundaryValuesToRanges {
    public HelperToGetBoundaryValuesToRanges() {
    }

    public float getProperValueOfTypeOfInputFromCountBMI(boolean imperialUnits, String type, int minOrMax) {
        if (type.equals("weight")) {
            return getWeightBoundaryValuesFromCounterBMI(imperialUnits, minOrMax);
        } else {
            return getHeightBoundaryValuesFromCounterBMI(imperialUnits, minOrMax);
        }
    }

    private float getWeightBoundaryValuesFromCounterBMI(boolean imperialUnits, int minOrMax) {
        if (imperialUnits) {
            return minOrMax == 0 ? CountBmiImperial.MIN_WEIGHT : CountBmiImperial.MAX_WEIGHT;
        } else {
            return minOrMax == 0 ? CountBmiMetrical.MIN_WEIGHT : CountBmiMetrical.MAX_WEIGHT;
        }
    }

    private float getHeightBoundaryValuesFromCounterBMI(boolean imperialUnits, int minOrMax) {
        if (imperialUnits) {
            return minOrMax == 0 ? CountBmiImperial.MIN_HEIGHT : CountBmiImperial.MAX_HEIGHT;
        } else {
            return minOrMax == 0 ? CountBmiMetrical.MIN_HEIGHT : CountBmiMetrical.MAX_HEIGHT;
        }
    }
}
