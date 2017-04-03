package krzyzowski.tomasz.lab2.lab2.Presenters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import krzyzowski.tomasz.lab2.lab2.MainActivity;
import krzyzowski.tomasz.lab2.lab2.ModelsOfPresentingResult.BMIGroupWithColor;
import krzyzowski.tomasz.lab2.lab2.PrepareLists.PrepareListOfBMIGroupWithColor;
import krzyzowski.tomasz.lab2.lab2.PrepareLists.PrepareListOfRanges;
import krzyzowski.tomasz.lab2.lab2.ModelsOfPresentingResult.Range;

/**
 * Created by tkrzy on 29.03.2017.
 */

public class ResultPresenter {
    public static final Map<Range, BMIGroupWithColor> groupMap=
            addGroupsAndRangesToMap(PrepareListOfRanges.getListOfRanges(),
                    PrepareListOfBMIGroupWithColor.getListOfBMIGroups());
    private NumberFormat nf;

    public ResultPresenter(int precision) {
        nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(precision);
    }

    public void showResult(TextView bmiResult, TextView bmiGroup, float bmi) {
        BMIGroupWithColor group = getBMIGroupFromBMI(bmi);
        bmiResult.setText(String.valueOf(nf.format(bmi)));
        bmiResult.setTextColor(group.getColor());
        bmiGroup.setText(group.getGroup());
        bmiGroup.setTextColor(group.getColor());
    }

    public boolean areInputsWithoutError(EditText weightInput, EditText heightInput) {
        return weightInput.getError() == null && heightInput.getError() == null;
    }


    private BMIGroupWithColor getBMIGroupFromBMI(float bmi) {
        BMIGroupWithColor properGroup = null;
        for (Map.Entry<Range, BMIGroupWithColor> entry : groupMap.entrySet()) {
            if (bmi >= entry.getKey().getFrom() && bmi <= entry.getKey().getTo()) {
                properGroup = entry.getValue();
                return entry.getValue();
            }
        }
        return properGroup;
    }
    public static Map<Range, BMIGroupWithColor>  addGroupsAndRangesToMap(List<Range> ranges, List<BMIGroupWithColor> groups) {
        Map<Range, BMIGroupWithColor> resultMap = new HashMap<>();
        int index = 0;
        for (Range range: ranges) {
            resultMap.put(range,groups.get(index++));
        }
        return resultMap;
    }
}
