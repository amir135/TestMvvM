package emir.g2.testmvvm.data.rest;

import javax.inject.Inject;

import io.reactivex.Single;
import emir.g2.testmvvm.data.model.BreedListResponse;
import emir.g2.testmvvm.data.model.Images;
import emir.g2.testmvvm.data.model.SubBreed;

public class DogRepository {

    private final ApiService apiService;

    @Inject
    public DogRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<BreedListResponse> getBreeds() {
        return apiService.getAllBreeds();
    }

    public Single<SubBreed> getSubBreed(String breed) {
        return apiService.getSubBreed(breed);
    }

    public Single<Images> getImages(String breed_subBreed) {
        return apiService.getImages(breed_subBreed);
    }
}
