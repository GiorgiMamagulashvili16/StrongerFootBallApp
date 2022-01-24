package com.example.strongerfootballapp.presentation.match_screen.adapter.helper

import com.example.strongerfootballapp.domain.model.Score
import com.example.strongerfootballapp.domain.model.Summary

object MatchScoreMapper {

    fun mapMatchHalves(half: Int, summaries: List<Summary>): Score {
        val score = Score()
        summaries.forEach {
            val isHalfTimeCorrect = when(half){
                FIRST_HALF -> it.actionTime.toInt() <= HALVES_DIVIDER_TIME
                else -> it.actionTime.toInt() > HALVES_DIVIDER_TIME
            }
            if (isHalfTimeCorrect){
                it.countGoals(score)
            }
        }
        return score
    }

    private const val FIRST_HALF = 1
    private const val HALVES_DIVIDER_TIME = 45

}