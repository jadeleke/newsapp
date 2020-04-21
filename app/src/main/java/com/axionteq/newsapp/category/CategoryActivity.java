package com.axionteq.newsapp.category;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.axionteq.newsapp.R;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    GridView gridView;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categoryArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_category );
        gridView = findViewById( R.id.gv_ac );

        Category();
        categoryAdapter = new CategoryAdapter( this, categoryArrayList );
        gridView.setAdapter( categoryAdapter );
        gridView.setNumColumns( 2 );
    }

    public void Category() {
        categoryArrayList.add( new Category( "News", R.color.colorCategory1, R.drawable.ic_news_24dp ) );
        categoryArrayList.add( new Category( "Lifestyle", R.color.colorCategory2, R.drawable.ic_fitness_24dp ) );
        categoryArrayList.add( new Category( "Travel", R.color.colorCategory3, R.drawable.ic_travel_24dp ) );
        categoryArrayList.add( new Category( "Health", R.color.colorCategory4, R.drawable.ic_health_24dp ) );
        categoryArrayList.add( new Category( "Entertainment", R.color.colorCategory5, R.drawable.ic_entertainment_24dp ) );
        categoryArrayList.add( new Category( "Food & Drink", R.color.colorCategory6, R.drawable.ic_food_24dp ) );
        categoryArrayList.add( new Category( "Cars", R.color.colorCategory7, R.drawable.ic_car_24dp ) );
        categoryArrayList.add( new Category( "Sport", R.color.colorCategory8, R.drawable.ic_sport_24dp ) );
        categoryArrayList.add( new Category( "Money", R.color.colorCategory9, R.drawable.ic_money_24dp ) );
        categoryArrayList.add( new Category( "Technology", R.color.colorCategory10, R.drawable.ic_technology_24dp ) );

    }
}
