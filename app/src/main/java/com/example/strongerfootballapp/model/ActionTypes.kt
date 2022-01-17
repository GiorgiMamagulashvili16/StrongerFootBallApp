package com.example.strongerfootballapp.model

import com.example.strongerfootballapp.R


enum class ActionTypes(val actionImageRes: Int, val actionTextRes: Int) {
    GOAL(R.drawable.ic_goal, R.string.goals_by),
    OWN_GOAL(R.drawable.ic_own_goal, R.string.own_goal),
    YELLOW_CARD(R.drawable.icyellow_card, R.string.tripping),
    RED_CARD(R.drawable.ic_red_card, R.string.tripping),
    SUBSTITUTION(R.drawable.ic_substitution, R.string.substitution)
}