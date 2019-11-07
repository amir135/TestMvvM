package emir.g2.testmvvm.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import emir.g2.testmvvm.di.util.ViewModelKey;
import emir.g2.testmvvm.ui.dogimages.DogImageViewModel;
import emir.g2.testmvvm.ui.fullscreenimage.fullscreenViewModel;
import emir.g2.testmvvm.ui.breed.BreedViewModel;

import emir.g2.testmvvm.ui.subbreed.SubBreedViewModel;
import emir.g2.testmvvm.util.ViewModelFactory;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BreedViewModel.class)
    abstract ViewModel bindBreedViewModel(BreedViewModel listViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SubBreedViewModel.class)
    abstract ViewModel bindSubBreedViewModel(SubBreedViewModel detailsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DogImageViewModel.class)
    abstract ViewModel bindDogImageViewModel(DogImageViewModel dogImageViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(fullscreenViewModel.class)
    abstract ViewModel bindtestViewModel(fullscreenViewModel dogImageViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
