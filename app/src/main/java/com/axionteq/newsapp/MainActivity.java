package com.axionteq.newsapp;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.axionteq.newsapp.adapter.NewsAdapter;
import com.axionteq.newsapp.data.NewsViewModel;
import com.axionteq.newsapp.db.AppDatabase;
import com.axionteq.newsapp.utils.Locals;
import com.facebook.stetho.Stetho;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    NewsViewModel newsViewModel;
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    public static Locals locals;
    public static AppDatabase appDatabase;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        toolbar = findViewById( R.id.tb );
        Stetho.initializeWithDefaults(this);

        setSupportActionBar( toolbar );
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle( getString( R.string.app_name ) );
        Objects.requireNonNull( getSupportActionBar() ).setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_arrow_back_black_24dp );

        locals = new Locals( this );
        locals.getCategory();
        appDatabase = Room.databaseBuilder( this,
                AppDatabase.class, "news_db" ).allowMainThreadQueries()
                .build();

        recyclerView = findViewById( R.id.recyclerView );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        recyclerView.addItemDecoration( new DividerItemDecoration(
                MainActivity.this,
                DividerItemDecoration.VERTICAL
        ) );

        newsViewModel = new ViewModelProvider( this ).get( NewsViewModel.class );
        newsAdapter = new NewsAdapter( this );

        newsViewModel.getNewsList().observe( this, (newsList) -> {
            newsAdapter.setNews( newsList );
        } );

        recyclerView.setAdapter( newsAdapter );
    }
}
