package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.SummaryDto
import com.example.strongerfootballapp.domain.model.Summary

class SummaryMapper(private val teamActionMapper: TeamActionMapper) : Mapper<SummaryDto, Summary> {
    override fun mapModel(model: SummaryDto): Summary {
        with(model) {
            return Summary(
                actionTime,
                teamActionMapper.mapToNullableList(team1Action),
                teamActionMapper.mapToNullableList(team2Action)
            )
        }
    }
}