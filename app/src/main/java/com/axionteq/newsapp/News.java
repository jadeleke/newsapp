package com.axionteq.newsapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName( "image" )
    @Expose
    private String imageurl;

    @SerializedName( "header" )
    @Expose
    private String title;

    @SerializedName( "id" )
    @Expose
    private String author;

    @SerializedName( "details" )
    @Expose
    private String content;

    @SerializedName("date")
    @Expose
    private String publish;

    public News(String author, String content, String title, String publish, String imageurl) {
        this.author = author;
        this.content = content;
        this.title = title;
        this.publish = publish;
        this.imageurl = imageurl;
    }

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
