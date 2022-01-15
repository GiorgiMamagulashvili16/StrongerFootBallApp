package com.example.strongerfootballapp.repository

import com.example.strongerfootballapp.model.Match
import com.example.strongerfootballapp.network.ApiService
import com.example.strongerfootballapp.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FootballRepositoryImpl(private val matchApi: ApiService) :
    FootballRepository {
    override suspend fun getMatch(): Response<Match> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = matchApi.getMatchPreview()
            if (response.isSuccessful) {
                val matchResponse = response.body()!!
                Response.success(matchResponse)
            } else {
                Response.error(response.message())
            }
        } catch (e: Exception) {
            Response.error(e.message!!)
        }
    }
}