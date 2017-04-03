package krzyzowski.tomasz.lab2.lab2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBMI;
import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBmiMetrical;
import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBmiImperial;
import krzyzowski.tomasz.lab2.lab2.Presenters.ErrorPresenter;
import krzyzowski.tomasz.lab2.lab2.Presenters.ResultPresenter;

public class MainActivity extends AppCompatActivity {
    public static String WEIGHT_KEY = "weight";
    public static String HEIGHT_KEY = "height";
    public static String COLOR_KEY = "color";
    public static String BMI_KEY = "bmi";

    @BindView(R.id.bmiResult)
    TextView bmiResult;
    @BindView(R.id.bmiGroup)
    TextView bmiGroup;
    @BindView(R.id.KgAndMetersUnitBtn)
    RadioButton btnMetrical;
    @BindView(R.id.LbAndFtUnitBtn)
    RadioButton btnImperial;
    @BindView(R.id.weightInput)
    EditText weightInput;
    @BindView(R.id.heightInput)
    EditText heightInput;

    private CountBMI countBMIForKgM;
    private CountBMI countBMIForLbFt;
    private ErrorPresenter errorPresenter;
    private ResultPresenter resultPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        errorPresenter = new ErrorPresenter();
        resultPresenter = new ResultPresenter(2);
        countBMIForKgM = new CountBmiMetrical();
        countBMIForLbFt = new CountBmiImperial();
        bmiResult.setText("0");
        errorPresenter.addTextChangedListenerToInput(weightInput, "weight", btnImperial);
        errorPresenter.addTextChangedListenerToInput(heightInput, "height", btnImperial);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(BMI_KEY, Float.valueOf(bmiResult.getText().toString()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        if (resultPresenter.areInputsWithoutError(weightInput, heightInput)) {
            resultPresenter.showResult(bmiResult, bmiGroup, inState.getFloat(BMI_KEY));
        }
    }


    @OnClick(R.id.btnCount)
    public void showBmiResult() {
        if (resultPresenter.areInputsWithoutError(weightInput, heightInput)) {
            CountBMI counterBMI = btnImperial.isChecked() ? countBMIForLbFt : countBMIForKgM;
            float bmi = counterBMI.countBmi(Float.valueOf(weightInput.getText().toString()),
                    Float.valueOf(heightInput.getText().toString()));
            resultPresenter.showResult(bmiResult, bmiGroup, bmi);
        }

    }
}
