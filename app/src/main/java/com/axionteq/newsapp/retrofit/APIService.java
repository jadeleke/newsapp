package com.axionteq.newsapp.retrofit;

import com.axionteq.newsapp.model.ArticlesResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("everything")
    Observable<Response<ArticlesResponse>> getArticles(
            @Query("q") String topic,
            @Query("apiKey") String apiKey
    );

}
