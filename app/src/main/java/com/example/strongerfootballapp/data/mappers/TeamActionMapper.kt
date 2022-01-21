package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.TeamActionDto
import com.example.strongerfootballapp.domain.model.TeamAction

class TeamActionMapper(private val actionMapper: ActionMapper) : Mapper<TeamActionDto, TeamAction> {
    override fun mapModel(model: TeamActionDto): TeamAction {
        with(model) {
            return TeamAction(
                actionMapper.mapModel(action),
                actionType,
                teamType
            )
        }
    }
}