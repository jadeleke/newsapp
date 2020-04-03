package com.axionteq.newsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName( "urlToImage" )
    @Expose
    private String imageurl;

    @SerializedName( "title" )
    @Expose
    private String title;

    @SerializedName( "author" )
    @Expose
    private String author;

    @SerializedName( "description" )
    @Expose
    private String content;

    @SerializedName("publishedAt")
    @Expose
    private String publish;

    public String getImageurl() {
        return imageurl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getPublish() {
        return publish;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
