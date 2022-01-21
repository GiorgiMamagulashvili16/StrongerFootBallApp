package com.example.strongerfootballapp.data.repository

import com.example.strongerfootballapp.data.mappers.MatchDtoMapper
import com.example.strongerfootballapp.data.network.api.ApiService
import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.repository.FootballRepository
import com.example.strongerfootballapp.domain.utils.Resource
import com.example.strongerfootballapp.domain.utils.fetchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class FootballRepositoryImpl(
    private val matchApi: ApiService,
    private val matchDtoMapper: MatchDtoMapper
) : FootballRepository {
    override suspend fun getMatch(): Resource<Match> = withContext(Dispatchers.IO) {
        return@withContext fetchData(response = { matchApi.getMatchPreview() }) { result ->
            val mappedData = matchDtoMapper.mapMatchDto(result)
            Resource.Success(mappedData)
        }
    }
}
