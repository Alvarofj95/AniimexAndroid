package com.aforce.aniimex.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("_id")
    @Expose
    val id: String,
    @SerializedName("category")
    @Expose
    val category: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("seasons")
    @Expose
    val seasons: String,
    @SerializedName("url")
    @Expose
    val url: String
)