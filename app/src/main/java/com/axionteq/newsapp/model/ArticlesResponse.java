package com.axionteq.newsapp.model;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.axionteq.newsapp.utils.NewsTypeConverters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@TypeConverters(NewsTypeConverters.class)
@Entity()
public class ArticlesResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("articles")
    @Expose
    private List<News> news;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
