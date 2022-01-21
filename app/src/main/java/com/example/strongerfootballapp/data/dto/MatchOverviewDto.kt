package com.example.strongerfootballapp.data.dto

data class MatchOverviewDto(
    val team1: TeamDto,
    val team2: TeamDto,
    val matchDate: Long,
    val matchTime: Double,
    val stadiumAdress: String,
    val matchSummary: MatchSummaryDto
)