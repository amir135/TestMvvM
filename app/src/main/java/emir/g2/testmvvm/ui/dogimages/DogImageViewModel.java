package emir.g2.testmvvm.ui.dogimages;

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
import emir.g2.testmvvm.data.model.Images;

public class DogImageViewModel extends ViewModel {

    private final DogRepository dogRepository;
    private CompositeDisposable disposable;
    private Breed selectedBreed;
    private String selectedSubBreed=null;
    private final MutableLiveData<Images> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public DogImageViewModel(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
        disposable = new CompositeDisposable();
    }
    public void setSelectedRepo(String subBread, Breed breed)
    {
        if(subBread == null || subBread=="")
        {
            selectedSubBreed=null;
        }
        selectedBreed=breed;



    }
    public void getSubBreeds()
    {
        fetchRepos();
    }
    LiveData<Images> getRepos() {
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
        String breed_SubBreed=selectedBreed.getType()+((selectedSubBreed!=null)? "/"+selectedSubBreed:"");
        disposable.add(dogRepository.getImages(breed_SubBreed).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<Images>() {
                    @Override
                    public void onSuccess(Images value) {
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
