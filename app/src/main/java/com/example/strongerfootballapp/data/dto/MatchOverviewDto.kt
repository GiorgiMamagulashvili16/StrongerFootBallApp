package com.example.strongerfootballapp.data.dto

import com.google.gson.annotations.SerializedName

data class MatchOverviewDto(
    val team1: TeamDto,
    val team2: TeamDto,
    val matchDate: Long,
    val matchTime: Double,
    @SerializedName("stadiumAdress")
    val stadiumAddress: String,
    val matchSummary: MatchSummaryDto
)