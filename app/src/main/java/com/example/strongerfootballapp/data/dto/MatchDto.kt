package com.example.strongerfootballapp.data.dto

import com.example.strongerfootballapp.domain.model.MatchOverview

data class MatchDto(
    val resultCode: Int,
    val match: MatchOverview
)
