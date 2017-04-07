package krzyzowski.tomasz.lab2.lab2;

import krzyzowski.tomasz.lab2.lab2.CounterBMI.CountBMI;

public class CalcBMIPresenter implements CalcBMIContract.Presenter {

	private CalcBMIContract.View view;
	private CountBMI counterBMI;

	@Override
	public void setView(CalcBMIContract.View view) {
		this.view = view;
	}
}
