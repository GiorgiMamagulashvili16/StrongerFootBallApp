package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.MatchSummaryDto
import com.example.strongerfootballapp.domain.model.MatchSummary

class MatchSummaryMapper(private val summaryMapper: SummaryMapper) :
    Mapper<MatchSummaryDto, MatchSummary> {
    override fun mapModel(model: MatchSummaryDto): MatchSummary {
        return MatchSummary(summaryMapper.mapToList(model.summaries))
    }
}