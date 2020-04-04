package com.axionteq.newsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.axionteq.newsapp.adapter.NewsAdapter;
import com.axionteq.newsapp.data.NewsViewModel;

public class MainActivity extends AppCompatActivity {

    NewsViewModel newsViewModel;
    NewsAdapter newsAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsAdapter = new NewsAdapter(this);

        newsViewModel.getNewsList().observe(this, (newsList) -> {
            newsAdapter.setNews(newsList);
        });

        recyclerView.setAdapter(newsAdapter);
    }
}
