package com.axionteq.newsapp.retrofit;

import com.axionteq.newsapp.model.Articles;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("everything/bitcoin")
    Observable<Articles> getArticles(
            @Query("q") String topic
    );

}
