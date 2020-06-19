package com.axionteq.newsapp;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.axionteq.newsapp.adapter.NewsAdapter;
import com.axionteq.newsapp.data.NewsRepository;
import com.axionteq.newsapp.data.NewsViewModel;
import com.axionteq.newsapp.db.AppDatabase;
import com.axionteq.newsapp.model.News;
import com.axionteq.newsapp.utils.Locals;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("ResultOfMethodCallIgnored")
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
        recyclerView = findViewById( R.id.recyclerView );

        locals = new Locals( this );
        locals.getCategory();

        setSupportActionBar( toolbar );
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle( locals.getCategory() );
        Objects.requireNonNull( getSupportActionBar() ).setDisplayShowHomeEnabled( true );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_arrow_back_white_24dp );

        appDatabase = Room.databaseBuilder( this,
                AppDatabase.class, "news_db" ).allowMainThreadQueries()
                .build();

        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        recyclerView.addItemDecoration( new DividerItemDecoration(
                MainActivity.this,
                DividerItemDecoration.VERTICAL
        ) );

        newsViewModel = new ViewModelProvider( this ).get( NewsViewModel.class );
        newsAdapter = new NewsAdapter( this );

        newsViewModel.getNewsList().observe( this, (List<News> newsList) -> newsAdapter.setNews( newsList ) );
        recyclerView.setAdapter( newsAdapter );
    }

    @Override
    protected void onPause() {
        super.onPause();
        NewsRepository.getInstance().safelyDisposable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.sm_search);
        searchItem.setActionView(R.layout.search_property_layout);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search News");


        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        assert searchManager != null;

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newsAdapter.filter( newText );
                return false;
            }
        });

        return true;
    }

}
