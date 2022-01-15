package com.example.strongerfootballapp.repository

import com.example.strongerfootballapp.model.Match
import com.example.strongerfootballapp.utils.Response


interface FootballRepository {
    suspend fun getMatch(): Response<Match>
}