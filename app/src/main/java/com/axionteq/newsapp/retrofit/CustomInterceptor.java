package com.axionteq.newsapp.retrofit;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CustomInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();
        Request newRequest = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .method(request.method(), null)
                .build();

        return chain.proceed(newRequest);
    }
}
