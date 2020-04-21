package com.axionteq.newsapp.utils;

import androidx.room.TypeConverter;

import com.axionteq.newsapp.model.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class NewsTypeConverters {

    @TypeConverter
    public static List<News> stringToNews(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<News>>() {
        }.getType();
        return gson.fromJson( json, type );
    }


    @TypeConverter
    public static String newsToString(List<News> list) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<News>>() {
        }.getType();
        String json = gson.toJson( list, type );
        return json;
    }

}
