package com.example.strongerfootballapp.domain.model

import kotlin.math.max

data class MatchSummary(
    val summaries: List<Summary>
) {
     fun getAdjustListForRecyclerView(): List<Summary> {
        val adjustedList = mutableListOf<Summary>()
        summaries.forEach { summary ->
            val team1ActionsSize = summary.team1Action?.size
            val team2ActionsSize = summary.team2Action?.size
            if (team1ActionsSize != 1 || team2ActionsSize != 1){
                val maxSize = max(team1ActionsSize?:0, team2ActionsSize?:0)
                (0 until maxSize).forEach {
                    val team1actionList = checkTeamAction(summary.team1Action, it)
                    val team2actionList = checkTeamAction(summary.team2Action, it)
                    adjustedList.add(Summary(summary.actionTime, team1actionList, team2actionList))
                }
            }else{
                adjustedList.add(summary)
            }
        }
        return adjustedList.toList()
    }

    private fun checkTeamAction(teamAction: List<TeamAction>?, index: Int): List<TeamAction>?{
        return try {
            val data = teamAction?.get(index)
            if (data == null) data else listOf(data)
        }catch (e: IndexOutOfBoundsException){
            null
        }
    }

}