package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.ActionDto
import com.example.strongerfootballapp.domain.model.Action

class ActionMapper(private val playerMapper: PlayerMapper) : Mapper<ActionDto, Action> {
    override fun mapModel(model: ActionDto): Action {
        with(model) {
            return Action(
                goalType,
                playerMapper.mapModel(player),
                playerMapper.mapModel(player1),
                playerMapper.mapModel(player2),
            )
        }
    }
}