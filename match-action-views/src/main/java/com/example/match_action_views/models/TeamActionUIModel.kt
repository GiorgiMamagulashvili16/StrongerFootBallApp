package com.example.match_action_views.models

data class TeamActionUIModel(
    val action: ActionUIModel,
    val actionType: ActionTypeUIModel?,
    val teamType: Int
)