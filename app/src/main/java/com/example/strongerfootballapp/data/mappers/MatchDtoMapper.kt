package com.example.strongerfootballapp.data.mappers

import com.example.strongerfootballapp.data.dto.*
import com.example.strongerfootballapp.domain.model.*

class MatchDtoMapper {

    fun mapMatchDto(matchDto: MatchDto): Match {
        return Match(mapMatchOverviewDtoMatchOverview(matchDto.match))
    }

    fun mapMatchOverviewDtoMatchOverview(matchOverviewDto: MatchOverviewDto): MatchOverview {
        return MatchOverview(
            mapTeamDtoToTeam(matchOverviewDto.team1),
            mapTeamDtoToTeam(matchOverviewDto.team2),
            matchOverviewDto.matchDate,
            matchOverviewDto.matchTime,
            matchOverviewDto.stadiumAdress,
            mapMatchSummaryDtoMatchSummary(matchOverviewDto.matchSummary)
        )
    }

    fun mapTeamDtoToTeam(teamDto: TeamDto): Team {
        return Team(teamDto.ballPosition, teamDto.score, teamDto.teamImage, teamDto.teamName)
    }

    fun mapMatchSummaryDtoMatchSummary(matchSummaryDto: MatchSummaryDto): MatchSummary {
        return MatchSummary(matchSummaryDto.summaries.map {
            mapSummaryDtoToSummary(it)
        })
    }

    fun mapSummaryDtoToSummary(summaryDto: SummaryDto): Summary {
        return Summary(
            summaryDto.actionTime,
            mapTeamActionDtoListToTeamActionList(summaryDto.team1Action),
            mapTeamActionDtoListToTeamActionList(summaryDto.team2Action)
        )
    }

    fun mapTeamActionDtoListToTeamActionList(teamActionListDto: List<TeamActionDto>?): List<TeamAction>? {
        return teamActionListDto?.let {
            it.map { teamActionDto ->
                TeamAction(
                    mapActionDtoToAction(teamActionDto.action),
                    teamActionDto.actionType,
                    teamActionDto.teamType
                )
            }
        }
    }

    fun mapActionDtoToAction(actionDto: ActionDto): Action {
        return Action(
            actionDto.goalType,
            mapPlayerDtoToPlayer(actionDto.player),
            mapPlayerDtoToPlayer(actionDto.player1),
            mapPlayerDtoToPlayer(actionDto.player2),
        )
    }

    fun mapPlayerDtoToPlayer(playerDto: PlayerDto?): Player? {
        return playerDto?.let {
            Player(playerDto.playerImage, playerDto.playerName)
        }
    }

}
