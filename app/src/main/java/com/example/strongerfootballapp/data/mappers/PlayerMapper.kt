package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.PlayerDto
import com.example.strongerfootballapp.domain.model.Player

class PlayerMapper : Mapper<PlayerDto?, Player?> {
    override fun mapModel(model: PlayerDto?): Player? {
        return model?.let {
            Player(it.playerImage, it.playerName)
        }
    }
}