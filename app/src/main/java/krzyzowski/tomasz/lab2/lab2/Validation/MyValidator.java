package krzyzowski.tomasz.lab2.lab2.Validation;

import krzyzowski.tomasz.lab2.lab2.ModelsOfPresentingResult.Range;

/**
 * Created by tkrzy on 29.03.2017.
 */

public class MyValidator {
    private Range validationRange;

    public MyValidator(Range validationRange) {

        this.validationRange = validationRange;
    }

    public Range getValidationRange() {
        return validationRange;
    }

    public boolean isEmpty(CharSequence s){
        return s.length() == 0;
    }
    public boolean isInRange(Range range,float num) {
        return num >= range.getFrom() && num <= range.getTo();
    }
    public boolean validate(CharSequence s) {
        return !isEmpty(s) && isInRange(validationRange,Float.valueOf(s.toString()));
    }


}
