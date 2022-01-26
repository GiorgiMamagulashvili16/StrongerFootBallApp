package com.example.strongerfootballapp.data.repository

import com.example.strongerfootballapp.data.mappers.MatchMapper
import com.example.strongerfootballapp.data.network.api.ApiService
import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.repository.FootballRepository
import com.example.strongerfootballapp.domain.utils.Resource
import com.example.strongerfootballapp.domain.utils.fetchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FootballRepositoryImpl(
    private val matchApi: ApiService,
    private val matchMapper: MatchMapper
) : FootballRepository {
    override suspend fun getMatch(): Resource<Match> = withContext(Dispatchers.IO) {
        return@withContext fetchData(responseCall = { matchApi.getMatchPreview() }) { result ->
            val mappedData = matchMapper.mapModel(result)
            Resource.Success(mappedData)
        }
    }
}
