package com.axionteq.newsapp.retrofit;

import com.axionteq.newsapp.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomInterceptor implements Interceptor {

    private final String API_KEY = BuildConfig.API_KEY;

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();

        Headers headers = new Headers.Builder()
                .add("Authorization", API_KEY)
                .build();

        Request newRequest = request.newBuilder()
                .addHeader("Authorization", API_KEY)
                .cacheControl(CacheControl.FORCE_CACHE)
                .headers(headers)
                .method(request.method(), null)
                .build();

        return chain.proceed(newRequest);
    }
}
