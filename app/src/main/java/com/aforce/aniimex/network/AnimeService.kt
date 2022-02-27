package com.aforce.aniimex.network

import com.aforce.aniimex.model.Anime
import com.aforce.aniimex.model.AnimeBody
import retrofit2.Call
import retrofit2.http.*

interface AnimeService {

    @GET("animes")
    fun getAnimes(): Call<List<Anime>>

    @GET("animes/{id}")
    fun getAnimeById(@Path("id") animeId: String): Call<Anime>

    @POST("addAnime/")
    fun createAnime(@Body anime: AnimeBody): Call<Any>

    @DELETE("deleteAnime/{id}")
    fun deleteAnimeById(@Path("id") animeId: String): Call<Void>



}