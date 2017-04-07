package krzyzowski.tomasz.lab2.lab2;

import krzyzowski.tomasz.lab2.lab2.bmi.*;
import krzyzowski.tomasz.lab2.lab2.repo.persist.CachedRepo;
import krzyzowski.tomasz.lab2.lab2.repo.TempData;
import krzyzowski.tomasz.lab2.lab2.repo.temp.TempRepo;

public class BMIPresenter implements BMIContract.Presenter {

    private BMIContract.View view;
    private BMICalculator counterBMI;

    private TempRepo tempRepo;
    private CachedRepo cachedRepo;

    BMIPresenter(TempRepo tempRepo, CachedRepo cachedRepo) {
        setUnits(Units.METRICAL);
        this.tempRepo = tempRepo;
        this.cachedRepo = cachedRepo;
    }

    @Override
    public void setView(BMIContract.View view) {
        this.view = view;
    }

    @Override
    public void setUnits(Units units) {
        counterBMI = units == Units.METRICAL ? new BMICalculatorMetrical() : new BMICalculatorImperial();
    }

    @Override
    public void onStart() {
        TempData tempData = cachedRepo.isEmpty() ? tempRepo.retrieve() : cachedRepo.retrieve();
        view.setData(tempData);
        if(tempData.hasResult()) {
            view.setSaveMIEnabled(true);
            view.setShareMIEnabled(true);
            view.setSharedData(tempData.getResult());
			cachedRepo.store(tempData);
        }
    }

    @Override
    public void onSaveClicked() {
        cachedRepo.flush();
    }

    @Override
    public void onAboutAuthorClicked() {
        view.navigateToAboutAuthorView();
    }

    @Override
    public boolean isWeightValid(float weight) {
        return counterBMI.isWeightValid(weight);
    }

    @Override
    public boolean isHeightValid(float height) {
        return counterBMI.isHeightValid(height);
    }

    @Override
    public Range getValidWeightRange() {
        return counterBMI.getWeightRange();
    }

    @Override
    public Range getValidHeightRange() {
        return counterBMI.getHeightRange();
    }

    @Override
    public void saveTempData(TempData tempData) {
        tempRepo.store(tempData);
    }

    @Override
    public void countBMI(float weight, float height) {
        float bmi = counterBMI.countBmi(weight, height);
        view.setResult(bmi);
        view.setSharedData(bmi);
        view.setSaveMIEnabled(true);
        view.setShareMIEnabled(true);
        cachedRepo.store(view.collectTempData());
    }
}
