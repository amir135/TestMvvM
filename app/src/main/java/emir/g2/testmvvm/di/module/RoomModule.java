package emir.g2.testmvvm.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;



@Module(includes = ViewModelModule.class)
public class RoomModule {

/*
    @Provides
    @Singleton
    AppDatabase provideBreedDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "test.db").build();
    }

    @Provides
    @Singleton
    QueryDao provideBreedDao(AppDatabase breedDatabase) {
        return breedDatabase.queryDao();
    }


    private final AppDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                AppDatabase.class,
                "ListItem.db"
        ).build();
    }

    @Provides
    @Singleton
    GlobalRepository provideListItemRepository(QueryDao listItemDao){
        return new GlobalRepository(listItemDao);
    }

    @Provides
    @Singleton
    QueryDao provideListItemDao(AppDatabase database){
        return database.queryDao();
    }

    @Provides
    @Singleton
    AppDatabase provideListItemDatabase(Application application){
        return database;
    }
*/


}