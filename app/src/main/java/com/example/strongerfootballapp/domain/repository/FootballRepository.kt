package com.example.strongerfootballapp.domain.repository

import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.utils.Response


interface FootballRepository {
    suspend fun getMatch(): Response<Match>
}