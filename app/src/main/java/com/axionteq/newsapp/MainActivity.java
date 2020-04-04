package com.axionteq.newsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.axionteq.newsapp.adapter.NewsAdapter;
import com.axionteq.newsapp.data.NewsViewModel;
import com.axionteq.newsapp.databinding.ActivityMainBinding;
import com.axionteq.newsapp.model.News;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NewsViewModel newsViewModel;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setMain(this);
        binding.setLifecycleOwner(this);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsAdapter = new NewsAdapter(getApplicationContext());

        newsViewModel.getNewsList().observe(this, (newsList) -> {
            newsAdapter.setNews(newsList);
            binding.recyclerView.setAdapter(newsAdapter);
        });
    }
}
