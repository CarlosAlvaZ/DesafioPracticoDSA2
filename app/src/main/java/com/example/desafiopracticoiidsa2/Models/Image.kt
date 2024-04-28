package com.example.desafiopracticoiidsa2.Models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url") val url: String,
    @SerializedName("height") val height: Int,
    @SerializedName("widht") val width: Int
)
