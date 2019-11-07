package emir.g2.testmvvm.data.rest;

import java.util.List;

import io.reactivex.Single;
import emir.g2.testmvvm.data.model.BreedListResponse;
import emir.g2.testmvvm.data.model.Images;
import emir.g2.testmvvm.data.model.SubBreed;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("breeds/list/all")
    Single<BreedListResponse> getAllBreeds();

    @GET("breed/{breed}/list")
    Single<SubBreed> getSubBreed(@Path("breed") String breed);

    @GET("breed/{breed}/images")
    Single<Images> getImages(@Path("breed") String breed);



}
