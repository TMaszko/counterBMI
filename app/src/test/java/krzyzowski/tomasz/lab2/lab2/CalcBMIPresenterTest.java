package krzyzowski.tomasz.lab2.lab2;

import org.junit.Before;
import org.junit.Test;

import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBmiImperial;
import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBmiMetrical;

import static org.mockito.Mockito.*;

/**
 * Created by tkrzy on 03.04.2017.
 */

public class CalcBMIPresenterTest {

    private static final String VALID_WEIGHT_TEXT = "70";
    private static final String INVALID_WEIGHT_TEXT = "9";

    private static final String VALID_HEIGHT_TEXT = "1.7";
    private static final String INVALID_HEIGHT_TEXT = "0.4";

    private CalcBMIContract.Presenter presenter = new CalcBMIPresenter();
    private CalcBMIContract.View view;

    @Before
    public void setup() {
        view = mock(CalcBMIContract.View.class);
        presenter.setView(view);
        presenter.onMetrical();
    }

    @Test
    public void emptyWeightDisablesCountButton() {
        presenter.onInputEdit("", VALID_HEIGHT_TEXT);
        verify(view).disableCountBtn();
    }

    @Test
    public void emptyHeightDisablesCountButton() {
        presenter.onInputEdit(VALID_WEIGHT_TEXT, "");
        verify(view).disableCountBtn();
    }

    @Test
    public void validInputsEnableCountButton() {
        presenter.onInputEdit(VALID_WEIGHT_TEXT, VALID_HEIGHT_TEXT);
        verify(view).enableCountBtn();
    }

    @Test
    public void invalidWeightAndValidHeightShowsErrorMsg() {
        presenter.onInputEdit(INVALID_WEIGHT_TEXT, VALID_HEIGHT_TEXT);
        verify(view).showWeightError(Float.valueOf(INVALID_WEIGHT_TEXT), CountBmiMetrical.MIN_WEIGHT, CountBmiMetrical.MAX_WEIGHT);
    }

    @Test
    public void invalidHeightAndValidWeightShowsErrorMsg() {
        presenter.onInputEdit(VALID_WEIGHT_TEXT, INVALID_HEIGHT_TEXT);
        verify(view).showHeightError(Float.valueOf(INVALID_HEIGHT_TEXT), CountBmiMetrical.MIN_HEIGHT, CountBmiMetrical.MAX_HEIGHT);
    }

    @Test
    public void invalidWeightAndValidHeightDisablesCountButton() {
        presenter.onInputEdit(INVALID_WEIGHT_TEXT, VALID_HEIGHT_TEXT);
        verify(view).disableCountBtn();
    }

    @Test
    public void invalidHeightDisablesCountButton() {
        presenter.onInputEdit(VALID_WEIGHT_TEXT, INVALID_HEIGHT_TEXT);
        verify(view).disableCountBtn();
    }

    @Test
    public void invalidWeightEmptyHeightShowErrorMsg() {
        presenter.onInputEdit(INVALID_WEIGHT_TEXT, "");
        verify(view).showWeightError(Float.valueOf(INVALID_WEIGHT_TEXT),CountBmiMetrical.MIN_WEIGHT, CountBmiMetrical.MAX_WEIGHT);
    }

    @Test
    public void invalidHeightEmptyWeightShowErrorMsg() {
        presenter.onInputEdit("",INVALID_HEIGHT_TEXT);
        verify(view).showHeightError(Float.valueOf(INVALID_HEIGHT_TEXT),CountBmiMetrical.MIN_HEIGHT, CountBmiMetrical.MAX_HEIGHT);
    }

}
