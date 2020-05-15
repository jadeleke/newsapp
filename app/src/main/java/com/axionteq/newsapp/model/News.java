package com.axionteq.newsapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.axionteq.newsapp.MainActivity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "news")
public class News {

    @Expose
    @PrimaryKey
    @ColumnInfo(name = "category")
    @SerializedName("category")
    @NonNull
    private String category = MainActivity.locals.getCategory();

    @SerializedName("author")
    @ColumnInfo(name="author")
    @Expose
    private String author;

    @SerializedName("title")
    @ColumnInfo(name="title")
    @Expose
    private String title;

    @SerializedName("description")
    @ColumnInfo(name="description")
    @Expose
    private String description;

    @SerializedName("url")
    @ColumnInfo(name="url")
    @Expose
    private String url;

    @SerializedName("urlToImage")
    @ColumnInfo(name="urlToImage")
    @Expose
    private String imageUrl;

    @SerializedName("publishedAt")
    @ColumnInfo(name="publishedAt")
    @Expose
    private String published;

    @SerializedName("content")
    @ColumnInfo(name = "content")
    @Expose
    private String content;

    @Expose
    @Embedded
    private Source source;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }
}
