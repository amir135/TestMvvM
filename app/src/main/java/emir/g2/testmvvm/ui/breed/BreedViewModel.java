package emir.g2.testmvvm.ui.breed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import emir.g2.testmvvm.data.rest.DogRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import emir.g2.testmvvm.data.model.BreedListResponse;

public class BreedViewModel extends ViewModel {

    private final DogRepository dogRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<BreedListResponse> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public BreedViewModel(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
        disposable = new CompositeDisposable();
        fetchRepos();
    }

    LiveData<BreedListResponse> getRepos() {
        return repos;
    }
    LiveData<Boolean> getError() {
        return repoLoadError;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchRepos() {
        loading.setValue(true);
        disposable.add(dogRepository.getBreeds().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<BreedListResponse>() {
                    @Override
                    public void onSuccess(BreedListResponse value) {
                        repoLoadError.setValue(false);
                        repos.setValue(value);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
