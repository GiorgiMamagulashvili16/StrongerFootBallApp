package com.example.strongerfootballapp.domain.model

data class Summary(
    val actionTime: String,
    val team1Action: List<TeamAction>? = null,
    val team2Action: List<TeamAction>? = null
){

    fun countGoals(initialScore: Score = Score()){
        team1Action?.forEach {teamAction ->
            if (teamAction.actionType == ActionTypes.GOAL.actionId
                && teamAction.action.goalType != ActionTypes.OWN_GOAL.goalType) initialScore.increaseFirstTeamScore()
            else if (teamAction.actionType == ActionTypes.GOAL.actionId
                && teamAction.action.goalType == ActionTypes.OWN_GOAL.goalType) initialScore.increaseSecondTeamScore()
        }

        team2Action?.forEach {teamAction ->
            if (teamAction.actionType == ActionTypes.GOAL.actionId
                && teamAction.action.goalType != ActionTypes.OWN_GOAL.goalType) initialScore.increaseSecondTeamScore()
            else if (teamAction.actionType == ActionTypes.GOAL.actionId
                && teamAction.action.goalType == ActionTypes.OWN_GOAL.goalType) initialScore.increaseFirstTeamScore()
        }
    }

}