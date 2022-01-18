package com.example.strongerfootballapp.utils

import com.example.strongerfootballapp.model.ActionTypes
import com.example.strongerfootballapp.model.Score
import com.example.strongerfootballapp.model.Summary
import com.example.strongerfootballapp.model.TeamAction

object Mapper {

    fun mapMatchHalves(half: Int, summaries: List<Summary>): Score {
        val score = Score()
        summaries.forEach {
            val isHalfTimeCorrect = when(half){
                FIRST_HALF -> it.actionTime.toInt() <= HALVES_DIVIDER_TIME
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
            if (teamAction.actionType == GOAL_TYPE
                && teamAction.action.goalType != OWN_GOAL_TYPE) goalAction()
            else if (teamAction.actionType == GOAL_TYPE
                && teamAction.action.goalType == OWN_GOAL_TYPE) ownGoalAction()
        }
    }

    fun mapIntToActionType(value: Int, goalType: Int?): ActionTypes?{
        return when (value){
            GOAL_TYPE -> if (goalType == OWN_GOAL_TYPE) ActionTypes.OWN_GOAL else ActionTypes.GOAL
            YELLOW_CARD_TYPE -> ActionTypes.YELLOW_CARD
            RED_CARD_TYPE -> ActionTypes.RED_CARD
            SUBSTITUTION_TYPE -> ActionTypes.SUBSTITUTION
            else -> null
        }
    }

    private const val GOAL_TYPE = 1
    private const val OWN_GOAL_TYPE = 2
    private const val YELLOW_CARD_TYPE = 2
    private const val RED_CARD_TYPE = 3
    private const val SUBSTITUTION_TYPE = 4
    private const val FIRST_HALF = 1
    private const val HALVES_DIVIDER_TIME = 45

}