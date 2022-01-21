package com.example.strongerfootballapp.domain.use_case

import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.utils.Response

interface GetMatchUseCase {
    suspend fun getMatch(): Response<Match>
}