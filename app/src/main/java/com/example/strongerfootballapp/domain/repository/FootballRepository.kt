package com.example.strongerfootballapp.data.repository

import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.utils.Response


interface FootballRepository {
    suspend fun getMatch(): Response<Match>
}