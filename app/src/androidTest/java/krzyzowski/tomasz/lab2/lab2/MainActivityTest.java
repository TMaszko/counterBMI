package krzyzowski.tomasz.lab2.lab2;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBmiImperial;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by tkrzy on 29.03.2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private String weightToBeTyped, heightToBeTyped;
    private String actualGroup;
    private boolean imperialUnits = false;

    public void setImperialUnits(boolean imperialUnits) {
        this.imperialUnits = imperialUnits;
    }

    private final float WEIGHT_METRICAL = 68f;
    private final float WEIGHT_IMPERIAL = 170f;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    private void initWeightAndHeight(float weight,float height,String actualGroup,boolean imperialUnits) {
        this.weightToBeTyped = String.valueOf(imperialUnits? weight* CountBmiImperial.ratioPoundsToKg: weight);
        this.heightToBeTyped = String.valueOf(imperialUnits? height * CountBmiImperial.ratioFeetsToMeters : height);
        this.actualGroup = actualGroup;
    }
    @Test
    public void showBmiGroupNormalWeightTest() {
        float height = 1.7f;
        float weight = WEIGHT_METRICAL;
        initWeightAndHeight(weight, height, MainActivity.getContext().getString(R.string.groupNormalWeight),this.imperialUnits);
        prepareBmiGroupTest(this.imperialUnits);
    }
    @Test
    public void showBmiGroupUnderweightTest() {
        float height = 2.0f;
        float weight = WEIGHT_METRICAL;
        initWeightAndHeight(weight, height, MainActivity.getContext().getString(R.string.groupUnderweight), this.imperialUnits);
        prepareBmiGroupTest(this.imperialUnits);
    }
    @Test
    public void showBmiGroupOverweightTest() {
        float height = 1.6f;
        float weight = WEIGHT_METRICAL;
        initWeightAndHeight(weight,height, MainActivity.getContext().getString(R.string.groupOverweight), this.imperialUnits);
        prepareBmiGroupTest(imperialUnits);
    }
    @Test
    public void showBmiGroupObeseWeightTest() {
        float height = 1.5f;
        float weight = WEIGHT_METRICAL;
        initWeightAndHeight(weight,height, MainActivity.getContext().getString(R.string.groupObeseWeight), this.imperialUnits);
        prepareBmiGroupTest(this.imperialUnits);
    }
    private void prepareBmiGroupTest(boolean imperialUnits) {
        if(imperialUnits) {
            onView(withText("LB/FT")).perform(click());
        }
        prepareInputWithText(R.id.weightInput, weightToBeTyped);
        prepareInputWithText(R.id.heightInput, heightToBeTyped);
        onView(withText("COUNT BMI")).perform(click());
        onView(withId(R.id.bmiGroup)).check(matches(withText(actualGroup)));
    }
    @Test
    public void showBmiGroupUnderweightTestImperial() {
       setImperialUnits(true);
        showBmiGroupUnderweightTest();
    }
    @Test
    public void showBmiGroupNormalWeightTestImperial() {
        setImperialUnits(true);
        showBmiGroupNormalWeightTest();
    }
    @Test
    public void showBmiGroupOverweightTestImperial() {
        setImperialUnits(true);
        showBmiGroupOverweightTest();
    }
    @Test
    public void showBmiGroupObeseWeightTestImperial() {
        setImperialUnits(true);
        showBmiGroupObeseWeightTest();
    }

    private void prepareInputWithText(int input,String textToBeTyped) {
        onView(withId(input)).perform(typeText(textToBeTyped),closeSoftKeyboard());
    }
}
