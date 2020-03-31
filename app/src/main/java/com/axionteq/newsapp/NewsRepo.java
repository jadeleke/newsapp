package com.axionteq.newsapp;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsRepo {

    private ArrayList<NewsVM> arrayList;
    private MutableLiveData<ArrayList<NewsVM>> arrayListMutableLiveData = new MutableLiveData<ArrayList<NewsVM>>();

    NewsRepo() { }

    MutableLiveData<ArrayList<NewsVM>> getArrayListMutableLiveData() {
        ApiService apiService = RetrofitClient.getApiService();

        Observable<ArrayList<News>> observable = apiService.getNews()
                .subscribeOn( Schedulers.newThread() )
                .observeOn( AndroidSchedulers.mainThread() );

        observable.subscribe( new Observer<ArrayList<News>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArrayList<News> newsArrayList) {

                NewsVM newsModel;
                News news;

                arrayList = new ArrayList<>();

                for (int i = 0; i < newsArrayList.size(); i++) {
                    news = new News(
                            newsArrayList.get( i ).getAuthor(),
                            newsArrayList.get( i ).getContent(),
                            newsArrayList.get( i ).getTitle(),
                            newsArrayList.get( i ).getPublish(),
                            newsArrayList.get( i ).getImageurl()
                    );
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
