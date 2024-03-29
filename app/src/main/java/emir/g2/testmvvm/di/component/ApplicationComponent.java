package emir.g2.testmvvm.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import emir.g2.testmvvm.base.BaseApplication;
import emir.g2.testmvvm.di.module.ActivityBindingModule;
import emir.g2.testmvvm.di.module.ApplicationModule;
import emir.g2.testmvvm.di.module.ContextModule;
import emir.g2.testmvvm.di.module.RoomModule;


@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();

    }
}