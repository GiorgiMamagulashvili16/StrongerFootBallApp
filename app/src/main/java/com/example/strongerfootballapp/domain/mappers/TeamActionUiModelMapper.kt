package com.example.strongerfootballapp.domain.mappers

import com.example.match_action_views.models.TeamActionUiModel
import com.example.strongerfootballapp.data.mappers.Mapper
import com.example.strongerfootballapp.domain.model.TeamAction

class TeamActionUiModelMapper(private val actionUiModelMapper: ActionUiModelMapper) :
    Mapper<TeamAction, TeamActionUiModel> {
    override fun mapModel(model: TeamAction): TeamActionUiModel {
        with(model) {
            return TeamActionUiModel(actionUiModelMapper.mapModel(action), actionType, teamType)
        }
    }
}