package com.example.strongerfootballapp.network

import com.example.strongerfootballapp.model.Match
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("v2/5b9264193300006b00205fb9")
    suspend fun getMatchPreview(): Response<Match>
}