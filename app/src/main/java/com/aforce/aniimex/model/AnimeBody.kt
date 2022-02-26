package com.aforce.aniimex.model

import com.google.gson.annotations.Expose

data class AnimeBody(
    @Expose
    val category: String,
    @Expose
    val description: String,
    @Expose
    val name: String,
    @Expose
    val seasons: String,
    @Expose
    val url: String
)