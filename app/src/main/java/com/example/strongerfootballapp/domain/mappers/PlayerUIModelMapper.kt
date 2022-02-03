package com.example.strongerfootballapp.domain.mappers

import com.example.match_action_views.models.PlayerUIModel
import com.example.strongerfootballapp.data.mappers.Mapper
import com.example.strongerfootballapp.domain.model.Player

class PlayerUIModelMapper:Mapper<Player?,PlayerUIModel?> {
    override fun mapModel(model: Player?): PlayerUIModel? {
        return  model?.let {
            PlayerUIModel(it.playerImage,it.playerName)
        }
    }
}