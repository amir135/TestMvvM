package emir.g2.testmvvm.ui.subbreed;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import emir.g2.testmvvm.data.rest.DogRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import emir.g2.testmvvm.data.model.Breed;
import emir.g2.testmvvm.data.model.SubBreed;

public class SubBreedViewModel extends ViewModel {

    private final DogRepository dogRepository;
    private CompositeDisposable disposable;
    private Breed selectedBreed;
    private final MutableLiveData<SubBreed> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public SubBreedViewModel(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
        disposable = new CompositeDisposable();
        //fetchRepos();

    }
    public void setSelectedRepo(Breed breed)
    {
        selectedBreed=breed;
    }
    public Breed getSelectedRepo()
    {
        return selectedBreed;
    }
    public void getSubBreeds()
    {
        fetchRepos();
    }
    LiveData<SubBreed> getRepos() {
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
        disposable.add(dogRepository.getSubBreed(selectedBreed.getType()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<SubBreed>() {
                    @Override
                    public void onSuccess(SubBreed value) {
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
