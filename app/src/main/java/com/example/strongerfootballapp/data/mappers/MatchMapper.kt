package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.MatchDto
import com.example.strongerfootballapp.domain.model.Match

class MatchMapper(private val matchOverviewMapper: MatchOverviewMapper) : Mapper<MatchDto, Match> {
    override fun mapModel(model: MatchDto): Match {
        return Match(matchOverviewMapper.mapModel(model.match))
    }
}
