package com.ithome11.jetpackmvvmdemo.main.net;

import com.ithome11.jetpackmvvmdemo.main.config.AppConfig;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NewstApiForm {

    @GET(AppConfig.API_GET_NEWS)
    Observable<String> forGetNews(
            @Query("apiKey") String apiKey
    );

}
