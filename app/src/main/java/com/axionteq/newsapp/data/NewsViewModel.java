package com.axionteq.newsapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.axionteq.newsapp.model.News;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private LiveData<List<News>> newsList;

    public NewsViewModel(@NonNull Application application) {
        super(application);

        NewsRepository repository = NewsRepository.getInstance();
        newsList = repository.getArticles();
    }

    public LiveData<List<News>> getNewsList() {
        return newsList;
    }
}
