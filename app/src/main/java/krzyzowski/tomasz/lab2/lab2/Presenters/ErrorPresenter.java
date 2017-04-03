package krzyzowski.tomasz.lab2.lab2.Presenters;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import krzyzowski.tomasz.lab2.lab2.Presenters.helpers.HelperToGetBoundaryValuesToRanges;
import krzyzowski.tomasz.lab2.lab2.Validation.MyValidator;
import krzyzowski.tomasz.lab2.lab2.ModelsOfPresentingResult.Range;
import krzyzowski.tomasz.lab2.lab2.Validation.UnitsTextWatcher;

/**
 * Created by tkrzy on 29.03.2017.
 */

public class ErrorPresenter {
    HelperToGetBoundaryValuesToRanges getterOfValues;
    static List<UnitsTextWatcher> listenersToButton = new ArrayList<>();

    public ErrorPresenter() {
        getterOfValues = new HelperToGetBoundaryValuesToRanges();
    }
    public void addTextChangedListenerToInput(EditText input, String type, RadioButton btn){
        float min_value = getterOfValues.getProperValueOfTypeOfInputFromCountBMI(false, type, 0);
        float maxValue = getterOfValues.getProperValueOfTypeOfInputFromCountBMI(false, type, 1);
        float minValueImperial = getterOfValues.getProperValueOfTypeOfInputFromCountBMI(true, type, 0);
        float maxValueImperial = getterOfValues.getProperValueOfTypeOfInputFromCountBMI(true, type, 1);
        UnitsTextWatcher temp = new UnitsTextWatcher(
                new MyValidator(
                        new Range(min_value,maxValue)),new MyValidator(new Range(minValueImperial,maxValueImperial)),
                input, btn);
        listenersToButton.add(temp);
        input.addTextChangedListener(temp);
        setUpWatchersToCheckingStatusOfButton(btn);
    }
    public void setUpWatchersToCheckingStatusOfButton(RadioButton btn) {
        btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for(UnitsTextWatcher textWatcher: listenersToButton) {
                    textWatcher.validateHelper(textWatcher.getWatchedInput().getText().toString(), isChecked);
                    Log.d("Zmiana stanu przycisku", "Wykonalo sie" + isChecked);
                }
            }
        });
    }
    public String formatErrorWithExpectOfValidatorRange(MyValidator validator) {
        NumberFormat nf = NumberFormat.getInstance(); // get instance
        nf.setMaximumFractionDigits(2);// set decimal places
        Float from = validator.getValidationRange().getFrom();
        Float to = validator.getValidationRange().getTo();
        String fromFormatted = nf.format(from);
        String toFormatted = nf.format(to);
        return "Value of input must be from " +
                fromFormatted + " - " + toFormatted;
    }
    public  String isEmptyErrorMessage() {
        return "Value of input must be not empty!";
    }
}
