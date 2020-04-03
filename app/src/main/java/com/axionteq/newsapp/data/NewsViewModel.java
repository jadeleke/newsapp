package com.axionteq.newsapp.data;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.axionteq.newsapp.model.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsViewModel extends ViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> imageurl = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> publish = new ObservableField<>();
    public ObservableField<String> author = new ObservableField<>();

    private MutableLiveData<ArrayList<NewsViewModel>> arrayListMutableLiveData = new MutableLiveData<>();
    private ArrayList<NewsViewModel> newsViewModelArrayList;

    public ObservableField<String> imageurl() {
        return imageurl;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.get().load( imageUrl ).into( imageView );
    }

    public NewsViewModel() {
        NewsRepo newsRepo = new NewsRepo();
        arrayListMutableLiveData = newsRepo.getArrayListMutableLiveData();
    }

    NewsViewModel(News news) {
        this.imageurl.set( news.getImageurl() );
        this.author.set( news.getAuthor() );
        this.content.set( news.getContent() );
        this.publish.set( news.getPublish() );
        this.title.set( news.getTitle() );
    }

    MutableLiveData<ArrayList<NewsViewModel>> getArrayListMutableLiveData() {

        return arrayListMutableLiveData;
    }
}
