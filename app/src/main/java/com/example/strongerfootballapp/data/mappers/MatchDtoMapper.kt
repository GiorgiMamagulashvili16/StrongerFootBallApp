package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.MatchDto
import com.example.strongerfootballapp.domain.model.Match

class MatchDtoMapper {

    fun mapMatchDto(matchDto: MatchDto):Match{
        return Match(matchDto.match)
    }

}