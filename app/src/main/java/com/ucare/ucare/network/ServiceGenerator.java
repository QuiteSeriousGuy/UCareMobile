package com.ucare.ucare.network;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Knight on 06/08/2016.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://192.168.43.105:81/ucareapi/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            int maxAge = 10; // read for 10 sec
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .header("Accept", "Application/JSON")
                    .build();
        }
    };


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass, Context context) {

        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 20 * 1024 * 1024; // 20 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        httpClient.cache(cache);
        httpClient.writeTimeout(10000, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);

        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }


}