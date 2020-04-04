package com.axionteq.newsapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.axionteq.newsapp.model.ArticlesResponse;
import com.axionteq.newsapp.model.News;
import com.axionteq.newsapp.retrofit.APIService;
import com.axionteq.newsapp.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class NewsRepository {

    private static NewsRepository instance;
    private APIService apiService;
    private CompositeDisposable disposable;

    private NewsRepository() {
        apiService = RetrofitClient.getApiService();
        disposable = new CompositeDisposable();
    }

    // Singleton
    static NewsRepository getInstance() {
        if (instance == null)
            instance = new NewsRepository();

        return instance;
    }

    LiveData<List<News>> getArticles() {
        final MutableLiveData<List<News>> newsLiveDataList = new MutableLiveData<>();

        disposable.add(apiService.getArticles("bitcoin")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(articlesResponse -> {
                    if (articlesResponse.body() != null && articlesResponse.isSuccessful()) {

                        ArticlesResponse response = articlesResponse.body();
                        if (response.getStatus().equals("ok"))
                            newsLiveDataList.setValue(response.getNews());
                        else
                            Log.i("Err", "Unable to contact API");
                    } else
                        Log.i("Err", articlesResponse.message());
                }, throwable -> Log.i("throwable", throwable.getLocalizedMessage()))
        );

        return newsLiveDataList;
    }

}