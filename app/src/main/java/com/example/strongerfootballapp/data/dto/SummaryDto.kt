package com.example.strongerfootballapp.data.dto

data class SummaryDto(
    val actionTime: String,
    val team1Action: List<TeamActionDto>? = null,
    val team2Action: List<TeamActionDto>? = null
)