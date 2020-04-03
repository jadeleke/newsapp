package com.axionteq.newsapp.retrofit;

import com.axionteq.newsapp.model.Articles;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIService {

    @GET("everything?q=bitcoin&apiKey=ad77d5f9a95d4c0a863a9c881c5fc6a9")
    Observable<Articles> getArticles();

}
