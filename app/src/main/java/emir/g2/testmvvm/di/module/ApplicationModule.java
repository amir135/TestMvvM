package emir.g2.testmvvm.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import emir.g2.testmvvm.data.rest.ApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    private static final String BASE_URL = "https://dog.ceo/api/";

    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    @Singleton
    @Provides
    static ApiService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
