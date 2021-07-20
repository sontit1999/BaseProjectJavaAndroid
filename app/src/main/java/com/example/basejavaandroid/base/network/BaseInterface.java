package com.example.basejavaandroid.base.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface BaseInterface {
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("page/{page}")
    Call<String> getIdolByPage(@Path("page") int page);
}
