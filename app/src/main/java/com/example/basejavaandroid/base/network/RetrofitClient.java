package com.example.basejavaandroid.base.network;

import android.content.Context;

import com.example.basejavaandroid.base.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;
    private RetrofitClient() {
    }

    public static BaseInterface getInterfaceClient(Context context) {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient.getApiInterface(context);
    }

    private Gson getGson() {
        return new GsonBuilder().setLenient().create();
    }

    private BaseInterface getApiInterface(Context context) {
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(getOkHttpClient(context))
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(BaseInterface.class);*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(getOkHttpClient(context))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return retrofit.create(BaseInterface.class);

    }

    private Cache provideCache(Context application) {
        long cacheSize = 10 * 1024 * 1024; // 10 MB
        File httpCacheDirectory = new File(application.getCacheDir(), "http-cache");
        return new Cache(httpCacheDirectory, cacheSize);
    }

    private OkHttpClient getOkHttpClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(provideCache(context))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        return builder.build();
    }
    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .baseUrl(Constant.BASE_URL)
                    .build();
        }
        return retrofit;
    }
    public static void cancelAllRequest() {
        try {
            retrofitClient = null;
        } catch (Exception e) {

        }
    }
}
