package com.example.strongerfootballapp.domain.model

import com.example.strongerfootballapp.R


enum class ActionTypes(
    val actionImageRes: Int,
    val actionTextRes: Int,
    val actionId: Int,
    val goalType: Int? = null
) {
    GOAL(R.drawable.ic_goal, R.string.goals_by, 1, 1),
    OWN_GOAL(R.drawable.ic_own_goal, R.string.own_goal, 1, 2),
    YELLOW_CARD(R.drawable.icyellow_card, R.string.tripping, 2),
    RED_CARD(R.drawable.ic_red_card, R.string.tripping, 3),
    SUBSTITUTION(R.drawable.ic_substitution, R.string.substitution, 4);

    companion object {
        fun getActionType(actionId: Int, goalType: Int?): ActionTypes? =
            values().find {
                it.actionId == actionId && it.goalType == goalType
            }
    }
}
