package com.axionteq.newsapp;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepo {

    private ArrayList<NewsVM> arrayList;
    private MutableLiveData<ArrayList<NewsVM>> arrayListMutableLiveData = new MutableLiveData<>();


    MutableLiveData<ArrayList<NewsVM>> getArrayListMutableLiveData() {
        ApiService apiService = RetrofitClient.getApiService();

        Observable<Articles> observable = apiService.getArticles()
                .subscribeOn( Schedulers.newThread() )
                .observeOn( AndroidSchedulers.mainThread() );

        observable.subscribe( new Observer<Articles>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Articles newsArrayList) {

                arrayList = new ArrayList<>();
                List<News> listNews = newsArrayList.getNews();
                NewsVM newsModel;

                for (int i = 0; i < listNews.size(); i++) {
                    News news = new News();
                    news.setTitle( listNews.get( i ).getTitle() );
                    news.setImageurl( listNews.get( i ).getImageurl() );
                    news.setPublish( listNews.get( i ).getPublish() );
                    news.setContent( listNews.get( i ).getContent() );
                    news.setAuthor( listNews.get( i ).getAuthor() );

                    newsModel = new NewsVM( news );
                    arrayList.add( newsModel );
                }
                arrayListMutableLiveData.setValue( arrayList );

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        } );
        return arrayListMutableLiveData;
    }

}