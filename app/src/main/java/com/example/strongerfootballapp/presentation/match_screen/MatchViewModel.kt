package com.example.strongerfootballapp.presentation.match_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.domain.model.Summary
import com.example.strongerfootballapp.domain.use_case.count_goal.CountGoalsUseCase
import com.example.strongerfootballapp.domain.use_case.get_match.GetMatchUseCase
import com.example.strongerfootballapp.domain.utils.Resource
import com.example.strongerfootballapp.domain.utils.ResourcesProvider
import com.example.strongerfootballapp.domain.utils.extensions.toFormattedDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MatchViewModel(
    private val getMatchUseCase: GetMatchUseCase,
    private val countGoalsUseCase: CountGoalsUseCase,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {
    private val _matchScreenStateFlow = MutableStateFlow<MatchScreenStates>(MatchScreenStates.Idle)
    val matchScreenStateFlow: StateFlow<MatchScreenStates> = _matchScreenStateFlow.asStateFlow()

//    private val _matchDateAndStadiumInfoFlow = MutableStateFlow<String?>(null)
//    val matchDateAndStadiumInfoFlow: StateFlow<String?> = _matchDateAndStadiumInfoFlow

    private val _matchTimeFlow = MutableStateFlow<String?>(null)
    val matchTimeFlow: StateFlow<String?> = _matchTimeFlow

    private val _firstTeamBallPossession = MutableStateFlow<String?>(null)
    val firstTeamBallPossession: StateFlow<String?> = _firstTeamBallPossession

    private val _secondTeamBallPossession = MutableStateFlow<String?>(null)
    val secondTeamBallPossession: StateFlow<String?> = _secondTeamBallPossession

    fun getMatch() {
        viewModelScope.launch {
            when (val matchResponse = getMatchUseCase.getMatch()) {
                is Resource.Success -> {
                    with(matchResponse.data) {
                        _matchScreenStateFlow.value =
                            MatchScreenStates.SuccessLoading(this)
//                        setMatchDateAndStadiumInfo(
//                            match.matchDate,
//                            match.stadiumAdress
//                        )
                        setMatchTime(match.matchTime)
                        setTeamBallPossessions(match.team1.ballPosition, match.team2.ballPosition)
                    }
                }


                is Resource.Error -> _matchScreenStateFlow.value =
                    MatchScreenStates.ErrorLoading(matchResponse.message)
            }
        }
    }

//    private fun setMatchDateAndStadiumInfo(matchDate: Long, stadiumAddress: String) =
//        viewModelScope.launch {
//            _matchDateAndStadiumInfoFlow.emit(
//                resourcesProvider.getString(
//                    R.string.date_and_stadium_name,
//                    matchDate.toFormattedDate(),
//                    stadiumAddress
//                )
//            )
//        }

    private fun setMatchTime(matchTime: Double) = viewModelScope.launch {
        _matchTimeFlow.emit("${matchTime.toInt()}'")
    }

    private fun setTeamBallPossessions(firstTeamPossession: Int, secondTeamPossession: Int) =
        viewModelScope.launch {
            _firstTeamBallPossession.emit(
                resourcesProvider.getString(
                    R.string.percent,
                    firstTeamPossession
                )
            )
            _secondTeamBallPossession.emit(
                resourcesProvider.getString(
                    R.string.percent,
                    secondTeamPossession
                )
            )
        }

    fun getHalfScore(half: Int, summaries: List<Summary>) =
        countGoalsUseCase.countGoals(half, summaries)
}