package com.axionteq.newsapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.axionteq.newsapp.model.News;

import java.util.List;

@Dao
public interface NewsDao {


/*
  @Query("SELECT * FROM news WHERE category = Category")
    LiveData<List<News>> getAll(String Category);

    @Query("SELECT * FROM news")
    List<News> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(News newsDb);

    @Insert()
    void insertAll(NewsDb newsDb);

    @Delete
    void delete(NewsDb newsDb);

    @Update
    void update(NewsDb newsDb);*/

    /*    @Query("SELECT * FROM news")
        List<News> getAll();

        @Insert()
        void insertAll(List<News> news);

        @Query( "SELECT * FROM news WHERE category=:Category" )
        List<News> news(String Category);

        @Query("DELETE FROM news")
        int deleteAll();*/

    @Query("SELECT * FROM news")
    List<News> getAllCategoryBased();

    @Query("DELETE FROM news")
    void deleteAll();

    @Insert()
    void insertAll(List<News> news);
}
