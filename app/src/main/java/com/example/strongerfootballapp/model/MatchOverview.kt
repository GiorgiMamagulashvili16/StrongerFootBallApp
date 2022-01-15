package com.example.strongerfootballapp.model

data class MatchOverview(
    val team1: Team,
    val team2: Team,
    val matchDate: Long,
    val matchTime: Double,
    val stadiumAdress: String,
    val matchSummary: MatchSummary
)