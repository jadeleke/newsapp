package com.axionteq.newsapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.axionteq.newsapp.model.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getAllCategoryBased();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNews(News news);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllNews(List<News> news);
}
