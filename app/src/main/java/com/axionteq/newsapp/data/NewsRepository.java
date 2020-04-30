package com.axionteq.newsapp.data;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.axionteq.newsapp.BuildConfig;
import com.axionteq.newsapp.MainActivity;
import com.axionteq.newsapp.model.ArticlesResponse;
import com.axionteq.newsapp.model.News;
import com.axionteq.newsapp.retrofit.APIService;
import com.axionteq.newsapp.retrofit.RetrofitClient;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepository {

    private static final String TAG = "NewsRepository";
    private final String API_KEY = BuildConfig.API_KEY;
    private String topic = MainActivity.locals.getCategory();

    private static NewsRepository instance;
    private APIService apiService;
    private CompositeDisposable compositeDisposable;
    Disposable disposable;

    private NewsRepository() {
        apiService = RetrofitClient.getApiService();
        compositeDisposable = new CompositeDisposable();
    }

    // Singleton
    public static NewsRepository getInstance() {
        if (instance == null)
            instance = new NewsRepository();

        return instance;
    }

    @SuppressLint("Assert")
    LiveData<List<News>> getArticles() {
        final MutableLiveData<List<News>> newsLiveDataList = new MutableLiveData<>();

        List<News> newsList = new ArrayList<>();

        News news = null;
        disposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( connectivity -> {
                            if (connectivity = true) {

        compositeDisposable.add( apiService.getArticles( topic, API_KEY )
                        .observeOn( AndroidSchedulers.mainThread() )
                        .subscribeOn( Schedulers.io() )
                        .subscribe( articlesResponse -> {
                            Log.d( TAG + " out", articlesResponse.toString() );
                            if (articlesResponse.body() != null && articlesResponse.isSuccessful()) {

                                ArticlesResponse response = articlesResponse.body();
                                if (response.getStatus().equals( "ok" )) {

                                    newsLiveDataList.setValue( MainActivity.appDatabase.userDao().getAllCategoryBased());

                                    newsLiveDataList.setValue( response.getNews() );
                                    newsList.addAll( response.getNews() );

                                    Insert(newsList);
                                    Log.d( TAG + " res", response.toString() );
                                } else
                                    Log.i( TAG, "Unable to contact API" );

                            } else
                                Log.i( TAG + " err", articlesResponse.message() );
                        }, throwable -> Log.i( TAG + " e", throwable.getMessage() ) )
        );
                          }
                        }
                );
        return newsLiveDataList;
    }

    private void Insert(List<News> newsList) {

        MainActivity.appDatabase.userDao().deleteAll();
        MainActivity.appDatabase.userDao().insertAll( newsList );
    }

        public void safelyDisposable(){
        safelyDispose( disposable );
    }

    private void safelyDispose(Disposable disposable){
        if (disposable!=null && !disposable.isDisposed()){
            disposable.dispose();
        }

    }
}