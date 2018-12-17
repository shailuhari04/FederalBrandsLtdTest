package com.federalbrandsltdtest.remote;

import com.federalbrandsltdtest.pojos.GetPhotosResponsePojo;
import io.reactivex.Observable;
import retrofit2.http.GET;

import java.util.List;

public interface ApiService {

    @GET("photos")
    Observable<List<GetPhotosResponsePojo>> getPhotos();

    @GET()
    Observable<GetPhotosResponsePojo> getPhotosData();
}
