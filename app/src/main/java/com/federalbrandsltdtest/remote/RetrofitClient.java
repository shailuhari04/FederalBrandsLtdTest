package com.federalbrandsltdtest.remote;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class RetrofitClient {

    //https://jsonplaceholder.typicode.com/photos
    private static final String WEB_API_BASE_URL = "https://jsonplaceholder.typicode.com";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(1000, TimeUnit.SECONDS)
            .connectTimeout(1000, TimeUnit.SECONDS)
            .build();

    public static ApiService getAPIService() {
        return new Retrofit.Builder().baseUrl(WEB_API_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }
}
