package krzyzowski.tomasz.lab2.lab2;

/**
 * Created by tkrzy on 03.04.2017.
 */

public class CalcBMIContract {

    private CalcBMIContract() {
    }

    interface View {


	}

    interface Presenter {
        void setView(View view);
    }

}
