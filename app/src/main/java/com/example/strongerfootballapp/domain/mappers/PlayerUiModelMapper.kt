package com.example.strongerfootballapp.domain.mappers

import com.example.match_action_views.models.PlayerUiModel
import com.example.strongerfootballapp.data.mappers.Mapper
import com.example.strongerfootballapp.domain.model.Player

class PlayerUiModelMapper:Mapper<Player?,PlayerUiModel?> {
    override fun mapModel(model: Player?): PlayerUiModel? {
        return  model?.let {
            PlayerUiModel(it.playerImage,it.playerName)
        }
    }
}