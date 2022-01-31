package com.example.strongerfootballapp.domain.mappers

import com.example.match_action_views.models.ActionUiModel
import com.example.strongerfootballapp.data.mappers.Mapper
import com.example.strongerfootballapp.domain.model.Action

class ActionUiModelMapper(private val playerUiModelMapper: PlayerUiModelMapper) :
    Mapper<Action, ActionUiModel> {
    override fun mapModel(model: Action): ActionUiModel {
        with(model) {
            return ActionUiModel(
                goalType,
                playerUiModelMapper.mapModel(player),
                playerUiModelMapper.mapModel(player1),
                playerUiModelMapper.mapModel(player2)
            )
        }
    }
}