package com.aforce.aniimex.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AnimeBody(
    @SerializedName("category")
    @Expose
    val category: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("came")
    @Expose
    val name: String,
    @SerializedName("seasons")
    @Expose
    val seasons: String,
    @SerializedName("url")
    @Expose
    val url: String
)