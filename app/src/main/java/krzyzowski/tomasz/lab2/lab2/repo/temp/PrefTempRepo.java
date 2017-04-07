package krzyzowski.tomasz.lab2.lab2.repo.temp;

import android.content.SharedPreferences;
import android.util.*;

import krzyzowski.tomasz.lab2.lab2.Units;
import krzyzowski.tomasz.lab2.lab2.repo.TempData;

public class PrefTempRepo implements TempRepo {

    private static final String WEIGHT_KEY = "WEIGHT";
    private static final String HEIGHT_KEY = "HEIGHT";
    private static final String RESULT_KEY = "RESULT";
    private static final String UNITS_KEY = "Units";

    private SharedPreferences prefs;

    public PrefTempRepo(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public void store(TempData tempData) {
        SharedPreferences.Editor editor = prefs.edit();

        if(tempData.hasWeight())
            editor.putFloat(WEIGHT_KEY, tempData.getWeight());
        else
            editor.remove(WEIGHT_KEY);

        if(tempData.hasHeight())
            editor.putFloat(HEIGHT_KEY, tempData.getHeight());
        else
            editor.remove(HEIGHT_KEY);

        if(tempData.hasResult())
            editor.putFloat(RESULT_KEY, tempData.getResult());
        else
            editor.remove(RESULT_KEY);

        if(tempData.hasUnits())
            editor.putInt(UNITS_KEY, tempData.getUnits().getId());
        else
            editor.remove(UNITS_KEY);

        editor.apply();
    }

    @Override
    public TempData retrieve() {
        TempData tempData = new TempData();

        if(prefs.contains(WEIGHT_KEY))
            tempData.setWeight(prefs.getFloat(WEIGHT_KEY, Float.NEGATIVE_INFINITY));
        if(prefs.contains(HEIGHT_KEY))
            tempData.setHeight(prefs.getFloat(HEIGHT_KEY, Float.NEGATIVE_INFINITY));
        if(prefs.contains(RESULT_KEY))
            tempData.setResult(prefs.getFloat(RESULT_KEY, Float.NEGATIVE_INFINITY));
        if(prefs.contains(UNITS_KEY))
            tempData.setUnits(Units.byId(prefs.getInt(UNITS_KEY, Integer.MIN_VALUE)));

        return tempData;
    }

    @Override
    public boolean isEmpty() {
        return prefs.getAll().isEmpty();
    }
}
