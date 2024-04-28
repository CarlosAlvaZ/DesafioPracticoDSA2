package com.example.desafiopracticoiidsa2.services

import com.example.desafiopracticoiidsa2.Models.TrackResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface SpotifyApiService {
    @GET("/v1/search")
    suspend fun searchByTrack(
        @Query("q") url: String,
        @Query("type") type: String,
    ): Response<TrackResponse>
}