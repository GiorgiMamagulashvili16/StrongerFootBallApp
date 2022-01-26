package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.MatchOverviewDto
import com.example.strongerfootballapp.domain.model.MatchOverview

class MatchOverviewMapper(
    private val teamMapper: TeamMapper,
    private val matchSummaryMapper: MatchSummaryMapper
) : Mapper<MatchOverviewDto, MatchOverview> {
    override fun mapModel(model: MatchOverviewDto): MatchOverview {
        with(model) {
            return MatchOverview(
                team1 = teamMapper.mapModel(team1),
                team2 = teamMapper.mapModel(team2),
                matchDate = matchDate,
                matchTime = matchTime,
                stadiumAdress = stadiumAddress,
                matchSummaryMapper.mapModel(matchSummary)
            )
        }
    }
}