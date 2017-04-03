package krzyzowski.tomasz.lab2.lab2.PrepareLists;

import java.util.ArrayList;
import java.util.List;

import krzyzowski.tomasz.lab2.lab2.ModelsOfPresentingResult.Range;

/**
 * Created by tkrzy on 29.03.2017.
 */

public class PrepareListOfRanges {
    public static List<Range> getListOfRanges() {
        List<Range> listOfRanges = new ArrayList<>();
        Range underweightRange = new Range(0f,18.49f);
        Range normalWeightRange = new Range(18.50f, 24.99f);
        Range overweightRange = new Range(24.99f, 29.99f);
        Range obeseWeightRange = new Range(30f, Float.MAX_VALUE);

        listOfRanges.add(underweightRange);
        listOfRanges.add(normalWeightRange);
        listOfRanges.add(overweightRange);
        listOfRanges.add(obeseWeightRange);

        return listOfRanges;
    }
}
