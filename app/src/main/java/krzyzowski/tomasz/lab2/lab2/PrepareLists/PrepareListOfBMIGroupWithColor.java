package krzyzowski.tomasz.lab2.lab2.PrepareLists;

import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import krzyzowski.tomasz.lab2.lab2.ModelsOfPresentingResult.BMIGroupWithColor;
import krzyzowski.tomasz.lab2.lab2.MainActivity;
import krzyzowski.tomasz.lab2.lab2.R;

/**
 * Created by tkrzy on 29.03.2017.
 */
// utworzyc obiekt , mapper na widoku
public class PrepareListOfBMIGroupWithColor {
    public static List<BMIGroupWithColor> getListOfBMIGroups() {
        List<BMIGroupWithColor> listOfBMIGroups = new ArrayList<>();

        BMIGroupWithColor underweightGroup = new BMIGroupWithColor(R.string.groupUnderweight,
                R.color.colorUnderweight);
        BMIGroupWithColor normalWeightGroup = new BMIGroupWithColor(R.string.groupNormalWeight,
                R.color.colorNormalWeight);
        BMIGroupWithColor overweightGroup = new BMIGroupWithColor(R.string.groupOverweight,
                R.color.colorOverweight);
        BMIGroupWithColor obeseWeightGroup = new BMIGroupWithColor(R.string.groupObeseWeight,
                R.color.colorObeseWeight);

        listOfBMIGroups.add(underweightGroup);
        listOfBMIGroups.add(normalWeightGroup);
        listOfBMIGroups.add(overweightGroup);
        listOfBMIGroups.add(obeseWeightGroup);

        return listOfBMIGroups;
    }
}
