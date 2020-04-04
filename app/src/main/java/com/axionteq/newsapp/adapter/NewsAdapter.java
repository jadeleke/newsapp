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

        holder.author.setText(news.getAuthor());
        holder.title.setText(news.getTitle());
        holder.desc.setText(news.getDescription());
        holder.date.setText(news.getPublished());
        Picasso.get().load(news.getImageUrl()).into(holder.imageView);
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
        TextView desc, title, date, author;

        NewsViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.news_iv);
            desc = view.findViewById(R.id.tv_description);
            title = view.findViewById(R.id.tv_title);
            date = view.findViewById(R.id.tv_published);
            author = view.findViewById(R.id.tv_author);

            view.setOnClickListener(v -> {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
