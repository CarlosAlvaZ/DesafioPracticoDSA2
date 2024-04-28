package com.example.desafiopracticoiidsa2.Models

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("album") val album: Album,
    @SerializedName("artists") val artists: List<Artist>,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)