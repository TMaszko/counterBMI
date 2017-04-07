package krzyzowski.tomasz.lab2.lab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalcBMIActivity extends AppCompatActivity implements CalcBMIContract.View {

	private static final String KEY_PARTIAL_PREF = "krzyzowski.tomasz.lab2.lab2.PARTIAL_PREF";
	public static String KEY_SHARED_PREF = "krzyzowski.tomasz.lab2.lab2.SHARED_PREF";

	@BindView(R.id.bmi_result_view) TextView bmiResultView;
	@BindView(R.id.bmi_description_view) TextView bmiDescriptionView;
	@BindView(R.id.metrical_btn) RadioButton metricalBtn;
	@BindView(R.id.imperial_btn) RadioButton imperialBtn;
	@BindView(R.id.weight_edit) EditText weightEdit;
	@BindView(R.id.height_edit) EditText heightEdit;
	@BindView(R.id.count_btn) Button countBtn;

	private MenuItem saveMI;
	private MenuItem shareMI;

	private CalcBMIContract.Presenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		presenter = new CalcBMIPresenter();
		presenter.setView(this);
	}


}
