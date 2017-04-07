package krzyzowski.tomasz.lab2.lab2;

import krzyzowski.tomasz.lab2.lab2.repo.TempData;

class BMIContract {

    private BMIContract() {}

    interface View {
        void setWeight(float width);
        void setHeight(float height);
        void setUnits(Units units);
        void setResult(float bmi);

        void setSaveMIEnabled(boolean enabled);
        void setShareMIEnabled(boolean enabled);

        void setData(TempData tempData);
        void setSharedData(float result);

        void navigateToAboutAuthorView();

        TempData collectTempData();
    }

    interface Presenter {
        void setView(View view);
        void setUnits(Units units);

        void onStart();

        void onSaveClicked();
        void onAboutAuthorClicked();

        boolean isWeightValid(float weight);
        boolean isHeightValid(float height);

        Range getValidWeightRange();
        Range getValidHeightRange();

        void saveTempData(TempData tempData);

        void countBMI(float weight, float height);
    }

}
