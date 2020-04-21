package com.axionteq.newsapp.category;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.axionteq.newsapp.MainActivity;
import com.axionteq.newsapp.R;
import com.axionteq.newsapp.utils.Locals;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Category> category;

    CategoryAdapter(Context context, ArrayList<Category> category) {
        this.category = category;
        this.context = context;
    }

    @Override
    public int getCount() {
        return category.size();
    }

    @Override
    public Object getItem(int i) {
        return category.get( i );
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams", "Assert"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        View views = layoutInflater.inflate( R.layout.gridview_category, viewGroup, false );

        RelativeLayout linearLayout = (RelativeLayout) views.findViewById( R.id.ll_gc );
        ImageView imageView = (ImageView) views.findViewById( R.id.iv_gc );
        TextView textView = (TextView) views.findViewById( R.id.tv_gc );

        imageView.setImageResource( category.get( i ).getImage() );
        textView.setText( category.get( i ).getTitle() );
        linearLayout.setBackgroundResource( category.get( i ).getBackground() );

        linearLayout.setOnClickListener( view1 -> {

            Locals locals = new Locals( context );
            locals.setCategory( category.get( i ).getTitle() );
            Intent intent = new Intent( context, MainActivity.class );
            context.startActivity( intent );
        } );
        return views;
    }


}
