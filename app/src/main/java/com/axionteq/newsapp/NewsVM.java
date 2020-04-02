package com.axionteq.newsapp;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class NewsVM extends ViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> imageurl = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> publish = new ObservableField<>();
    public ObservableField<String> author = new ObservableField<>();

    private MutableLiveData<ArrayList<NewsVM>> arrayListMutableLiveData = new MutableLiveData<>();
    private ArrayList<NewsVM> newsVMArrayList;

    public ObservableField<String> imageurl() {
        return imageurl;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.get().load( imageUrl ).into( imageView );
    }

    public NewsVM() {
        NewsRepo newsRepo = new NewsRepo();
        arrayListMutableLiveData = newsRepo.getArrayListMutableLiveData();
    }

    NewsVM(News news) {
        this.imageurl.set( news.getImageurl() );
        this.author.set( news.getAuthor() );
        this.content.set( news.getContent() );
        this.publish.set( news.getPublish() );
        this.title.set( news.getTitle() );
    }

    MutableLiveData<ArrayList<NewsVM>> getArrayListMutableLiveData() {

        return arrayListMutableLiveData;
    }
}
