package krzyzowski.tomasz.lab2.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.*;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.*;
import java.util.*;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import krzyzowski.tomasz.lab2.lab2.bmi.BMIRange;
import krzyzowski.tomasz.lab2.lab2.bmi.BMIRanges;
import krzyzowski.tomasz.lab2.lab2.repo.persist.CachedRepo;
import krzyzowski.tomasz.lab2.lab2.repo.persist.CachedRepoImp;
import krzyzowski.tomasz.lab2.lab2.repo.temp.PrefTempRepo;
import krzyzowski.tomasz.lab2.lab2.repo.TempData;
import krzyzowski.tomasz.lab2.lab2.repo.temp.TempRepo;

public class BMIActivity extends AppCompatActivity implements BMIContract.View {

    private static final String PREF_TEMP = "kzyzu.nie.lubi.ryzu.PREF";
    private static final String PREF_CACHED = "maly.mis.nie.spi.dzis.CHACHED";

    @BindView(R.id.bmi_result_view)
    TextView resultView;
    @BindView(R.id.bmi_description_view)
    TextView descriptionView;
    @BindView(R.id.unitsGroup)
    RadioGroup unitsGroup;
    @BindView(R.id.metrical_btn)
    RadioButton metricalBtn;
    @BindView(R.id.imperial_btn)
    RadioButton imperialBtn;
    @BindView(R.id.weight_edit)
    EditText weightEdit;
    @BindView(R.id.height_edit)
    EditText heightEdit;
    @BindView(R.id.count_btn)
    Button countBtn;

    private MenuItem saveMI;
    private MenuItem shareMI;

    private ShareActionProvider shareActionProvider;
    private BMIContract.Presenter presenter;

    private boolean validWeight = false;
    private boolean validHeight = false;
    private Float result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TempRepo tempRepo = new PrefTempRepo(getSharedPreferences(PREF_TEMP, MODE_PRIVATE));
        CachedRepo cachedRepo = new CachedRepoImp(new PrefTempRepo(getSharedPreferences(PREF_CACHED, MODE_PRIVATE)));
        presenter = new BMIPresenter(tempRepo, cachedRepo);
        presenter.setView(this);
        updateCountBtnEnabled();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.saveTempData(collectTempData());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        saveMI = menu.findItem(R.id.mi_save);
        shareMI = menu.findItem(R.id.mi_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareMI);
        presenter.onStart();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_save:
                presenter.onSaveClicked();
                return true;
            case R.id.mi_aboutAuthor:
                presenter.onAboutAuthorClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void navigateToAboutAuthorView() {
        Intent intent = new Intent(this, AboutAuthorActivity.class);
        startActivity(intent);
    }

    @Override
    public void setWeight(float weight) {
        weightEdit.setText(formatFloat(weight));
    }

    @Override
    public void setHeight(float height) {
        heightEdit.setText(formatFloat(height));
    }

	private String formatFloat(float value) {
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(2);
		return format.format(value);
	}

	@Override
    public void setUnits(Units units) {
        int btnId = units == Units.METRICAL ? R.id.metrical_btn : R.id.imperial_btn;
        unitsGroup.check(btnId);
    }

    @Override
    public void setResult(float bmi) {
        this.result = bmi;
        BMIRange range = BMIRanges.getRange(bmi);
        resultView.setText(getString(R.string.bmi_result, bmi));
        descriptionView.setText(range.getTextId());

        int color = ContextCompat.getColor(this, range.getColorId());
        resultView.setTextColor(color);
        descriptionView.setTextColor(color);
        setSharedData(bmi);
        setShareMIEnabled(true);
    }

    @Override
    public void setSaveMIEnabled(boolean enabled) {
        saveMI.setEnabled(enabled);
    }

    @Override
    public void setShareMIEnabled(boolean enabled) {
        shareMI.setEnabled(enabled);
    }

    @Override
    public void setData(TempData tempData) {
        if(tempData.hasUnits())
            setUnits(tempData.getUnits());
        if(tempData.hasWeight())
            setWeight(tempData.getWeight());
        if(tempData.hasHeight())
            setHeight(tempData.getHeight());
        if(tempData.hasResult())
            setResult(tempData.getResult());
    }

    @Override
    public void setSharedData(float result) {
        if (shareActionProvider != null) {
            Intent shareIntent = new Intent();
            shareIntent.setType("text/plain");
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.shared_message, result));
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.shared_subject));
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    @OnTextChanged(R.id.weight_edit)
    void onWeightEditChanged() {
        validateWeight();
        updateCountBtnEnabled();
    }

    @OnTextChanged(R.id.height_edit)
    void onHeightEditChanged() {
        validateHeight();
        updateCountBtnEnabled();
    }

    @OnCheckedChanged(R.id.metrical_btn)
    void onUnitsChanged() {
        presenter.setUnits(getUnits());
        validateWeight();
        validateHeight();
        updateCountBtnEnabled();
    }

    @OnClick(R.id.count_btn)
    void onCountClicked() {
        presenter.countBMI(parseWeight(), parseHeight());
    }

    private void updateCountBtnEnabled() {
        boolean bothParamsValid  = validWeight && validHeight;
        countBtn.setEnabled(bothParamsValid);
    }

    private void validateWeight() {
        if(isWeightFilled()) {
            float weight = parseWeight();
            validWeight = presenter.isWeightValid(weight);
            updateWeightError();
        } else
            validWeight = false;
    }

    private void validateHeight() {
        if(isHeightFilled()) {
            float height = parseHeight();
            validHeight = presenter.isHeightValid(height);
            updateHeightError();
        } else
            validHeight = false;
    }

    private boolean isWeightFilled() {
        return !weightEdit.getText().toString().isEmpty();
    }

    private boolean isHeightFilled() {
        return !heightEdit.getText().toString().isEmpty();
    }

    private boolean isResultFilled() {
        return result != null;
    }

    private void updateWeightError() {
        if(validWeight)
            weightEdit.setError(null);
        else
            weightEdit.setError(formatWeightError());
    }

    private void updateHeightError() {
        if(validHeight) {
            heightEdit.setError(null);
        } else
            heightEdit.setError(formatHeightError());
    }

    private String formatWeightError() {
        Range validRange = presenter.getValidWeightRange();
        return getString(R.string.error_weight, parseWeight(), validRange.getLowerBound(), validRange.getUpperBound());
    }

    private String formatHeightError() {
        Range validRange = presenter.getValidHeightRange();
        return getString(R.string.error_height, parseHeight(), validRange.getLowerBound(), validRange.getUpperBound());
    }

    private float parseWeight() {
        String weightText = weightEdit.getText().toString();
        weightText = weightText.replace(",", ".");
        return Float.valueOf(weightText);
    }

    private float parseHeight() {
        String heightText = heightEdit.getText().toString();
        heightText = heightText.replace(",", ".");
        return Float.valueOf(heightText);
    }

    private Units getUnits() {
		Units units = metricalBtn.isChecked() ? Units.METRICAL : Units.IMPERIAL;

        return units;
    }

    @Override
    public TempData collectTempData() {
        TempData tempData = new TempData();

        if(isWeightFilled())
            tempData.setWeight(parseWeight());
        if(isHeightFilled())
            tempData.setHeight(parseHeight());
        if(isResultFilled())
            tempData.setResult(result);
        tempData.setUnits(getUnits());

        return tempData;
    }
}
