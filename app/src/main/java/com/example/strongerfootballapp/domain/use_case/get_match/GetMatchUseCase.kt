package com.example.strongerfootballapp.domain.use_case.get_match

import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.utils.Resource

interface GetMatchUseCase {
    suspend fun getMatch(): Resource<Match>
}