package krzyzowski.tomasz.lab2.lab2.repo.persist;

import krzyzowski.tomasz.lab2.lab2.repo.temp.TempRepo;

public interface CachedRepo extends TempRepo {

    void flush();

}
