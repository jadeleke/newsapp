package com.axionteq.newsapp.retrofit;

import com.axionteq.newsapp.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = BuildConfig.BASE_URL;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static OkHttpClient client = new OkHttpClient().newBuilder()
            .addNetworkInterceptor(new CustomInterceptor())
            .addInterceptor(new HttpLoggingInterceptor())
            .build();

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static APIService getApiService() {
        return getRetrofitInstance().create(APIService.class);
    }
}
