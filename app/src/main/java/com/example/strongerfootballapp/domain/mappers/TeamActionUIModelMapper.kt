package com.example.strongerfootballapp.domain.mappers

import com.example.match_action_views.models.TeamActionUIModel
import com.example.strongerfootballapp.data.mappers.Mapper
import com.example.strongerfootballapp.domain.model.ActionTypes
import com.example.strongerfootballapp.domain.model.TeamAction

class TeamActionUIModelMapper(
    private val actionUIModelMapper: ActionUIModelMapper,
    private val actionTypeUIModelMapper: ActionTypeUIModelMapper
) : Mapper<TeamAction, TeamActionUIModel> {
    override fun mapModel(model: TeamAction): TeamActionUIModel {
        val actionType = ActionTypes.getActionType(model.actionType, model.action.goalType)
            ?.let { actionTypeUIModelMapper.mapModel(it) }
        with(model) {
            return TeamActionUIModel(actionUIModelMapper.mapModel(action), actionType, teamType)
        }
    }
}