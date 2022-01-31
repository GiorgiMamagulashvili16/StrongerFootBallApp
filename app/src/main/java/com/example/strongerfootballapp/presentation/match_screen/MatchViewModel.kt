package com.example.strongerfootballapp.presentation.match_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strongerfootballapp.domain.model.Summary
import com.example.strongerfootballapp.domain.use_case.count_goal.CountGoalsUseCase
import com.example.strongerfootballapp.domain.use_case.get_match.GetMatchUseCase
import com.example.strongerfootballapp.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MatchViewModel(
    private val getMatchUseCase: GetMatchUseCase,
    private val countGoalsUseCase: CountGoalsUseCase
) : ViewModel() {
    private val _matchScreenStateFlow = MutableStateFlow<MatchScreenStates>(MatchScreenStates.Idle)
    val matchScreenStateFlow: StateFlow<MatchScreenStates> = _matchScreenStateFlow.asStateFlow()

    fun getMatch() {
        viewModelScope.launch {
            when (val matchResponse = getMatchUseCase.getMatch()) {
                is Resource.Success -> _matchScreenStateFlow.value =
                    MatchScreenStates.SuccessLoading(matchResponse.data)

                is Resource.Error -> _matchScreenStateFlow.value =
                    MatchScreenStates.ErrorLoading(matchResponse.message)
            }
        }
    }

    fun getHalfScore(half: Int, summaries: List<Summary>) = countGoalsUseCase.countGoals(half, summaries)

}