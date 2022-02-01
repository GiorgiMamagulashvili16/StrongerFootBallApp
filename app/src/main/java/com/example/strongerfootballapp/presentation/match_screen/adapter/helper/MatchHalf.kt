package com.example.strongerfootballapp.presentation.match_screen.adapter.helper

import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.domain.model.Score

class MatchHalf(private var half: Int) {
    private var hasGameStarted = false

    private fun isCurrentHalfTime(time: Int): Boolean {
        return time <= half * HALF_DIVIDER_TIME
    }

    fun determineCorrespondingScore(firstHalfScore: Score?, secondHalfScore: Score?): Score? {
        return when(half){
            FIRST_HALF -> firstHalfScore
            else -> secondHalfScore
        }
    }

    fun hasHalfStarted(time: Int): Boolean {
        return when {
            !hasGameStarted -> {
                hasGameStarted = true
                false
            }
            !isCurrentHalfTime(time) -> {
                half++
                false
            }
            else -> true
        }
    }

    fun getHalfIndicator() = when (half) {
        FIRST_HALF -> R.string.first_half
        else -> R.string.second_half
    }

    companion object {
        private const val HALF_DIVIDER_TIME = 45
        private const val FIRST_HALF = 1
    }
}