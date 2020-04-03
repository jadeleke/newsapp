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

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    ArrayList<NewsViewModel> arrayList;
    LayoutInflater layoutInflater;

    NewsAdapter(Context context, ArrayList<NewsViewModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from( viewGroup.getContext() );
        }
        NewsAdapterBinding binding = DataBindingUtil.inflate( layoutInflater, R.layout.recyclerview, viewGroup, false );
        return new NewsViewHolder( binding );
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsViewModel newsModel = arrayList.get( position );
        holder.bind( newsModel );
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        NewsAdapterBinding binding;

        public NewsViewHolder(NewsAdapterBinding binding) {
            super( binding.getRoot() );
            this.binding = binding;
        }

        public void bind(NewsViewModel newsModel) {
            this.binding.setAdapterMain( newsModel );
            binding.executePendingBindings();
        }

        public NewsAdapterBinding getBinding() {
            return binding;
        }
    }
}
