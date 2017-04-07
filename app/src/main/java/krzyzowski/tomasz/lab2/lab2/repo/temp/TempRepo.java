package krzyzowski.tomasz.lab2.lab2.repo.temp;

import krzyzowski.tomasz.lab2.lab2.repo.TempData;

public interface TempRepo {

    void store(TempData tempData);
    TempData retrieve();
    boolean isEmpty();
}
