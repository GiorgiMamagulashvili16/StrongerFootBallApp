package com.example.strongerfootballapp.data.repository

import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.data.network.api.ApiService
import com.example.strongerfootballapp.domain.repository.FootballRepository
import com.example.strongerfootballapp.domain.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FootballRepositoryImpl(private val matchApi: ApiService) :
    FootballRepository {
    override suspend fun getMatch(): Response<Match> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = matchApi.getMatchPreview()
            if (response.isSuccessful) {
                val matchResponse = response.body()!!
                Response.Success(matchResponse)
            } else {
                Response.Error(response.message())
            }
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }
}