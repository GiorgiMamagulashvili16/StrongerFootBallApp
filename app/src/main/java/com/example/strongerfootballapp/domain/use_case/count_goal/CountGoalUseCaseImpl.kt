package com.example.strongerfootballapp.domain.use_case.count_goal

import com.example.strongerfootballapp.domain.model.Score
import com.example.strongerfootballapp.domain.model.ActionTypes
import com.example.strongerfootballapp.domain.model.Summary
import com.example.strongerfootballapp.domain.model.TeamAction

class CountGoalUseCaseImpl : CountGoalsUseCase {
    private lateinit var score: Score

    override fun countGoals(half: Int, summaries: List<Summary>): Score {
        score = Score()
        summaries.forEach {
            val isHalfTimeCorrect = when (half) {
                FIRST_HALF -> it.actionTime.toInt() <= HALVES_DIVIDER_TIME
                else -> it.actionTime.toInt() > HALVES_DIVIDER_TIME
            }
            if (isHalfTimeCorrect) {
                countGoals(it.team1Action, it.team2Action)
            }
        }
        return score
    }

    private fun countGoals(team1Action: List<TeamAction>?, team2Action: List<TeamAction>?) {
        team1Action?.forEach { teamAction ->
            if (teamAction.actionType == ActionTypes.GOAL.actionId
                && teamAction.action.goalType != ActionTypes.OWN_GOAL.goalType
            ) score.increaseFirstTeamScore()
            else if (teamAction.actionType == ActionTypes.GOAL.actionId
                && teamAction.action.goalType == ActionTypes.OWN_GOAL.goalType
            ) score.increaseSecondTeamScore()
        }

        team2Action?.forEach { teamAction ->
            if (teamAction.actionType == ActionTypes.GOAL.actionId
                && teamAction.action.goalType != ActionTypes.OWN_GOAL.goalType
            ) score.increaseSecondTeamScore()
            else if (teamAction.actionType == ActionTypes.GOAL.actionId
                && teamAction.action.goalType == ActionTypes.OWN_GOAL.goalType
            ) score.increaseFirstTeamScore()
        }
    }

    companion object {
        private const val FIRST_HALF = 1
        private const val HALVES_DIVIDER_TIME = 45
    }
}