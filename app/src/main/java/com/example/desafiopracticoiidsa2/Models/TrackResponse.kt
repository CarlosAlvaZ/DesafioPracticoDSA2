package com.example.desafiopracticoiidsa2.Models

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<Track>
)

