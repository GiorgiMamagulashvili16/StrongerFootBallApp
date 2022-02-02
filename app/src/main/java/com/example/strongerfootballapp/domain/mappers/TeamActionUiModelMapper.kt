package com.example.strongerfootballapp.domain.mappers

import com.example.match_action_views.models.TeamActionUiModel
import com.example.strongerfootballapp.data.mappers.Mapper
import com.example.strongerfootballapp.domain.model.ActionTypes
import com.example.strongerfootballapp.domain.model.TeamAction

class TeamActionUiModelMapper(
    private val actionUiModelMapper: ActionUiModelMapper,
    private val actionTypeUIModelMapper: ActionTypeUIModelMapper
) : Mapper<TeamAction, TeamActionUiModel> {
    override fun mapModel(model: TeamAction): TeamActionUiModel {
        val actionType = ActionTypes.getActionType(model.actionType, model.action.goalType)
            ?.let { actionTypeUIModelMapper.mapModel(it) }
        with(model) {
            return TeamActionUiModel(actionUiModelMapper.mapModel(action), actionType, teamType)
        }
    }
}