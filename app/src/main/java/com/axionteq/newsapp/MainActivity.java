package com.axionteq.newsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.axionteq.newsapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NewsVM newsVM = new NewsVM(  );
    News news;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_main );

        binding.setMain( this );
        binding.setLifecycleOwner( this );
        newsVM = new ViewModelProvider( this ).get( NewsVM.class );

        binding.recyclerView.setHasFixedSize( true );
        binding.recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

        newsVM.getArrayListMutableLiveData().observe( this, new Observer<ArrayList<NewsVM>>() {
            @Override
            public void onChanged(ArrayList<NewsVM> newsVMS) {

                newsAdapter = new NewsAdapter( MainActivity.this, newsVMS );
                binding.recyclerView.setAdapter( newsAdapter );
            }
        } );
    }
}
