package com.axionteq.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.axionteq.newsapp.R;
import com.axionteq.newsapp.data.NewsViewModel;
import com.axionteq.newsapp.databinding.NewsAdapterBinding;
import com.axionteq.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        NewsAdapterBinding binding = DataBindingUtil.inflate(inflater, R.layout.recyclerview,
                viewGroup, false);
        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setNews(List<News> news) {
        newsList = news;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        NewsAdapterBinding binding;

        NewsViewHolder(NewsAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(News news) {
            this.binding.setAdapterMain(news);
            binding.executePendingBindings();
        }

        public NewsAdapterBinding getBinding() {
            return binding;
        }
    }
}
