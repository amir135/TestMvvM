package emir.g2.testmvvm.base;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import emir.g2.testmvvm.di.component.ApplicationComponent;
import emir.g2.testmvvm.di.component.DaggerApplicationComponent;

public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }


}
