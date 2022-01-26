package com.example.strongerfootballapp.domain.use_case

import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.repository.FootballRepository
import com.example.strongerfootballapp.domain.utils.Resource

class GetMatchUseCaseImpl(private val footballRepository: FootballRepository) : GetMatchUseCase {
    override suspend fun getMatch(): Resource<Match> {
        return footballRepository.getMatch()
    }
}