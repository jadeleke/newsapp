package com.axionteq.newsapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.axionteq.newsapp.model.News;
import com.axionteq.newsapp.utils.NewsTypeConverters;

@Database(entities = {News.class}, version = 1, exportSchema = false)
//@TypeConverters({NewsTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract NewsDao userDao();

/*    private static volatile AppDatabase database;

    public static AppDatabase getInstance(final Context context) {
        if (database == null) {
            synchronized (AppDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder( context.getApplicationContext(),
                            AppDatabase.class, "news_db" )
                            .build();
                }
            }
        }
        return database;
    }*/

}