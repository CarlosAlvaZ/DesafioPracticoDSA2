package com.example.desafiopracticoiidsa2.Models

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("album_type") val albumType: String,
    @SerializedName("total_tracks") val totalTracks: Int,
    @SerializedName("id") val id: String,
    @SerializedName("images") val images: List<Image>,
    @SerializedName("name") val name: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("release_date_precision") val releaseDatePrecision: String,
    @SerializedName("type") val type: String,
    @SerializedName("artists") val artists: List<Artist>
)