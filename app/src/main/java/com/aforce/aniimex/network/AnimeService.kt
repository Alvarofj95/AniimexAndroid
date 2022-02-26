package com.aforce.aniimex.network

import com.aforce.aniimex.model.Anime
import com.aforce.aniimex.model.AnimeBody
import retrofit2.Call
import retrofit2.http.*

interface AnimeService {

    @GET("animes")
    fun getAnimes(): Call<List<Anime>>

    @GET("anime/{id}")
    fun getAnimeById(@Path("animeId") animeId: String): Call<Anime>

    @POST("addAnime/")
    fun createAnime(@Body anime: AnimeBody): Call<Any>

    @DELETE("deleteAnime/{id}")
    fun deleteAnimeById(@Path("animeId") animeId: String): Call<Void>



}