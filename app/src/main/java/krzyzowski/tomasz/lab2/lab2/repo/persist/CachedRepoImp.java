package krzyzowski.tomasz.lab2.lab2.repo.persist;

import krzyzowski.tomasz.lab2.lab2.repo.TempData;
import krzyzowski.tomasz.lab2.lab2.repo.temp.TempRepo;

public class CachedRepoImp implements CachedRepo {

    private TempRepo repo;
    private TempData cachedData;

    public CachedRepoImp(TempRepo repo) {
        this.repo = repo;
    }

    @Override
    public void store(TempData tempData) {
        this.cachedData = tempData;
    }

    @Override
    public TempData retrieve() {
        return repo.retrieve();
    }

    @Override
    public boolean isEmpty() {
        return repo.isEmpty();
    }

    @Override
    public void flush() {
        repo.store(cachedData);
    }
}
