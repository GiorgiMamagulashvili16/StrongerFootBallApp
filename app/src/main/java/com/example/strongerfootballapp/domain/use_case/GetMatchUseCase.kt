package com.example.strongerfootballapp.domain.use_case

import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.repository.FootballRepository
import com.example.strongerfootballapp.domain.utils.Response

class GetMatchUseCase(private val footballRepository: FootballRepository) {

    suspend operator fun invoke():Response<Match>{
        return footballRepository.getMatch()
    }

}