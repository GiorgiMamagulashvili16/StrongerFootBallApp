package com.example.strongerfootballapp.domain.repository

import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.utils.Resource


interface FootballRepository {
    suspend fun getMatch(): Resource<Match>
}