package com.axionteq.newsapp;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/files/annoucement.php")
    Observable<ArrayList<News>> getNews();
}
