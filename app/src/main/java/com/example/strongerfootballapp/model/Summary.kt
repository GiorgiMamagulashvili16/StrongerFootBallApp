package com.example.strongerfootballapp.model

data class Summary(
    val actionTime: String,
    val team1Action: List<TeamAction>? = null,
    val team2Action: List<TeamAction>? = null
)