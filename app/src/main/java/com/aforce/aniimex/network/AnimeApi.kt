package com.aforce.aniimex.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object AnimeApi {
    private val logging = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build();

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://aniimex.herokuapp.com/api/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(AnimeService::class.java)
}