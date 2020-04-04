package com.axionteq.newsapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.axionteq.newsapp.BuildConfig;
import com.axionteq.newsapp.model.ArticlesResponse;
import com.axionteq.newsapp.model.News;
import com.axionteq.newsapp.retrofit.APIService;
import com.axionteq.newsapp.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

class NewsRepository {

    private static final String TAG = "NewsRepository";
    private final String API_KEY = BuildConfig.API_KEY;

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

        disposable.add(apiService.getArticles("bitcoin", API_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(articlesResponse -> {
                    Log.d(TAG + " out", articlesResponse.toString());
                    if (articlesResponse.body() != null && articlesResponse.isSuccessful()) {

                        ArticlesResponse response = articlesResponse.body();
                        if (response.getStatus().equals("ok")) {
                            newsLiveDataList.setValue(response.getNews());
                            Log.d(TAG + " res", response.toString());
                        } else
                            Log.i(TAG, "Unable to contact API");

                    } else
                        Log.i(TAG + " err", articlesResponse.message());
                }, throwable -> Log.i(TAG + " e", throwable.getMessage()))
        );

        return newsLiveDataList;
    }

}