package com.axionteq.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axionteq.newsapp.R;
import com.axionteq.newsapp.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context) {
        this.context = context;
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

        holder.sourceName.setText(news.getSource().get("name").getAsString());
        holder.title.setText(news.getTitle());
        holder.desc.setText(news.getDescription());
        holder.date.setText(news.getPublished());
        Picasso.get()
                .load(news.getImageUrl())
                .transform(new RoundedCornersTransformation(20, 0))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    public void setNews(List<News> news) {
        this.newsList = news;
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView desc, title, date, sourceName;

        NewsViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.news_iv);
            desc = view.findViewById(R.id.tv_description);
            title = view.findViewById(R.id.tv_title);
            date = view.findViewById(R.id.tv_published);
            sourceName = view.findViewById(R.id.tv_source_name);

            view.setOnClickListener(v -> {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
