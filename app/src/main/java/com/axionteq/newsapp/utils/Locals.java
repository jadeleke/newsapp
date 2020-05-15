package com.axionteq.newsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class Locals {

    private SharedPreferences sharedPreferences;

    public Locals(Context context) {
        sharedPreferences = context.getSharedPreferences( "Category", Context.MODE_PRIVATE );

    }

    public String getCategory() {
        return sharedPreferences.getString( "category", null );
    }

    public void setCategory(String category) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "category", category );
        editor.apply();
    }


}
