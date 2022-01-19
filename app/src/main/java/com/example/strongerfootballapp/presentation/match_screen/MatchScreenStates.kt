package com.example.strongerfootballapp.presentation.match_screen

import com.example.strongerfootballapp.domain.model.Match

sealed class MatchScreenStates{
    object Idle: MatchScreenStates()
    data class SuccessLoading(val data: Match): MatchScreenStates()
    data class ErrorLoading(val message: String?): MatchScreenStates()
}
