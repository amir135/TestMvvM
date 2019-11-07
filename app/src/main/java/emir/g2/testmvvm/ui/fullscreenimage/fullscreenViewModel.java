package emir.g2.testmvvm.ui.fullscreenimage;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import emir.g2.testmvvm.data.rest.DogRepository;
import io.reactivex.disposables.CompositeDisposable;
import emir.g2.testmvvm.data.model.SubBreed;

public class fullscreenViewModel extends ViewModel {

    private final DogRepository dogRepository;
    private CompositeDisposable disposable;
    private String selectedUrl;
    private final MutableLiveData<SubBreed> repos = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public fullscreenViewModel(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
        disposable = new CompositeDisposable();
        //fetchRepos();

    }
    public void setUrl(String url)
    {
        selectedUrl=url;



    }
    public String getUrl()
    {
        return selectedUrl;
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


    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
