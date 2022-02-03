package com.example.strongerfootballapp.domain.mappers

import com.example.match_action_views.models.ActionUIModel
import com.example.strongerfootballapp.data.mappers.Mapper
import com.example.strongerfootballapp.domain.model.Action

class ActionUIModelMapper(private val playerUIModelMapper: PlayerUIModelMapper) :
    Mapper<Action, ActionUIModel> {
    override fun mapModel(model: Action): ActionUIModel {
        with(model) {
            return ActionUIModel(
                goalType,
                playerUIModelMapper.mapModel(player),
                playerUIModelMapper.mapModel(player1),
                playerUIModelMapper.mapModel(player2)
            )
        }
    }
}