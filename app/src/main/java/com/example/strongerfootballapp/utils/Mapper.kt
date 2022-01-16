package com.example.strongerfootballapp.utils

import com.example.strongerfootballapp.model.Score
import com.example.strongerfootballapp.model.Summary
import com.example.strongerfootballapp.model.TeamAction

object Mapper {

    fun mapMatchHalves(half: Int, summaries: List<Summary>): Score {
        val score = Score()
        summaries.forEach {
            val isHalfTimeCorrect = when(half){
                FIRST_HALF -> it.actionTime.toInt() < HALVES_DIVIDER_TIME
                else -> it.actionTime.toInt() > HALVES_DIVIDER_TIME
            }
            if (isHalfTimeCorrect){
                countScore(it.team1Action, { score.increaseFirstTeamScore() }, { score.increaseSecondTeamScore() })
                countScore(it.team2Action, { score.increaseSecondTeamScore() }, { score.increaseFirstTeamScore() })
            }
        }
        return score
    }

    private fun countScore(actions: List<TeamAction>?, goalAction: () -> Unit, ownGoalAction: () -> Unit){
        actions?.forEach { teamAction ->
            if (teamAction.actionType == GOAL_ACTION_CODE
                && teamAction.action.goalType != OWN_GOAL_CODE) goalAction()
            else if (teamAction.actionType == GOAL_ACTION_CODE
                && teamAction.action.goalType == OWN_GOAL_CODE) ownGoalAction()
        }
    }

    private const val OWN_GOAL_CODE = 2
    private const val GOAL_ACTION_CODE = 1
    private const val FIRST_HALF = 1
    private const val HALVES_DIVIDER_TIME = 45

}