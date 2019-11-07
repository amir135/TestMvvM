package emir.g2.testmvvm.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import emir.g2.testmvvm.ui.dogimages.DogImageFragment;
import emir.g2.testmvvm.ui.fullscreenimage.fullscreenFragment;
import emir.g2.testmvvm.ui.breed.BreedFragment;
import emir.g2.testmvvm.ui.subbreed.SubBreedFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract BreedFragment provideListFragment();

    @ContributesAndroidInjector
    abstract SubBreedFragment provideDetailsFragment();

    @ContributesAndroidInjector
    abstract DogImageFragment provideDogImageFragment();

    @ContributesAndroidInjector
    abstract fullscreenFragment providefullscreenFragment();
}
