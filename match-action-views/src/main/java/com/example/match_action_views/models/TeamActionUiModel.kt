package com.example.match_action_views.models

data class TeamActionUiModel(
    val action: ActionUiModel,
    val actionType: ActionTypeUIModel?,
    val teamType: Int
)