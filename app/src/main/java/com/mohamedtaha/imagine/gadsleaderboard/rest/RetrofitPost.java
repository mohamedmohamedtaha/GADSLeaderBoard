package com.mohamedtaha.imagine.gadsleaderboard.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitPost {
    private static Retrofit retrofit = null;
    private static final String BASE_URL_GIT = "https://docs.google.com/forms/d/e/";
    static Gson gson = new GsonBuilder().setLenient().create();
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS).build();
    public static Retrofit getRetrofitGitHub() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL_GIT)
                     .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
}
