package krzyzowski.tomasz.lab2.lab2.Validation;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioButton;

import krzyzowski.tomasz.lab2.lab2.Presenters.ErrorPresenter;
import krzyzowski.tomasz.lab2.lab2.Validation.MyValidator;

/**
 * Created by tkrzy on 29.03.2017.
 */

public class UnitsTextWatcher implements TextWatcher {
    private MyValidator validatorInMetricalUnits;
    private MyValidator validatorInImperialUnits;
    private EditText watchedInput;

    public EditText getWatchedInput() {
        return watchedInput;
    }

    private RadioButton toggleBtnOfUnits;
    private ErrorPresenter errorPresenter;

    public boolean isImperialUnits() {
        return toggleBtnOfUnits.isChecked();
    }


    public UnitsTextWatcher(MyValidator validatorInMetricalUnits, MyValidator validatorInImperialUnits, final EditText watchedInput, final RadioButton toggleBtnOfUnits) {
        this.validatorInMetricalUnits = validatorInMetricalUnits;
        this.validatorInImperialUnits = validatorInImperialUnits;
        this.watchedInput = watchedInput;
        this.toggleBtnOfUnits = toggleBtnOfUnits;
        this.errorPresenter = new ErrorPresenter();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        validateHelper(s, isImperialUnits());

    }
    @Override
    public void afterTextChanged(Editable s) {

    }
    public void validateHelper(CharSequence s, boolean imperialUnits) {
        MyValidator validator = imperialUnits? this.validatorInImperialUnits : this.validatorInMetricalUnits;
        if (!validator.isEmpty(s)) {
            if (!validator.validate(s)) {
                setErrorsInWatchedInputs(errorPresenter.formatErrorWithExpectOfValidatorRange(validator));
            } else {
                watchedInput.setError(null);
            }
        } else {
            setErrorsInWatchedInputs(errorPresenter.isEmptyErrorMessage());
        }
    }


    private void setErrorsInWatchedInputs(String msg) {
        watchedInput.setError(msg);
    }
}
