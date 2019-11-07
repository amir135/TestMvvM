package emir.g2.testmvvm.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import emir.g2.testmvvm.ui.main.MainActivity;
import emir.g2.testmvvm.ui.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
