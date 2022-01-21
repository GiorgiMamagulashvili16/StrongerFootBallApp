package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.TeamDto
import com.example.strongerfootballapp.domain.model.Team

class TeamMapper : Mapper<TeamDto, Team> {
    override fun mapModel(model: TeamDto): Team {
        with(model) {
            return Team(ballPosition, score, teamImage, teamName)
        }
    }
}