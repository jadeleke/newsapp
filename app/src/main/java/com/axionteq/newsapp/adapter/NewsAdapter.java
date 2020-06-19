package com.axionteq.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axionteq.newsapp.R;
import com.axionteq.newsapp.detail.DetailActivity;
import com.axionteq.newsapp.model.News;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private List<News> newsList;
    private ArrayList<News> newsArrayList;

    public NewsAdapter(Context context) {
        this.context = context;
        this.newsArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recyclerview, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        String publishedDate = "";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                    Locale.getDefault()).parse(news.getPublished());
            assert date != null;
            publishedDate = new SimpleDateFormat("EE, dd MMM yy, hh:mm aaa", Locale.getDefault()).format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.sourceName.setText(news.getSource().getName());
        holder.title.setText(news.getTitle());
        holder.desc.setText(news.getDescription());
        holder.date.setText(publishedDate);
        Picasso.get()
                .load(news.getImageUrl())
                .transform(new RoundedCornersTransformation(20, 0))
                .into(holder.imageView);

        holder.linearLayout.setOnClickListener( v -> {
            Intent intent = new Intent( context, DetailActivity.class );
            intent.putExtra( "title", news.getTitle() );
            intent.putExtra( "url", news.getUrl() );
            intent.putExtra( "techname", news.getSource().getName() );
            context.startActivity( intent );
        } );
    }

    public void filter(String filter){
        filter = filter.toLowerCase(Locale.getDefault());
        newsList.clear();
        if (filter.length() == 0){
            newsArrayList.addAll( newsList );
        }else {
            for (News news:newsList){
                if ( news.getTitle().toLowerCase(Locale.getDefault()).contains(filter)){
                    newsList.add( news );
                }
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    public void setNews(List<News> news) {
        this.newsList = news;
        newsArrayList.addAll( news );
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView desc, title, date, sourceName;
        LinearLayout linearLayout;

        NewsViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.news_iv);
            desc = view.findViewById(R.id.tv_description);
            title = view.findViewById(R.id.tv_title);
            date = view.findViewById(R.id.tv_published);
            sourceName = view.findViewById(R.id.tv_source_name);
            linearLayout = view.findViewById( R.id.ll_rv );
        }
    }
}
