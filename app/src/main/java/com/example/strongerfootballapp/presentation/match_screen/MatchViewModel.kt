package com.example.strongerfootballapp.presentation.match_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strongerfootballapp.domain.repository.FootballRepository
import com.example.strongerfootballapp.domain.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MatchViewModel(private val footballRepository: FootballRepository) : ViewModel() {
    private val _matchScreenStateFlow = MutableStateFlow<MatchScreenStates>(MatchScreenStates.Idle)
    val matchScreenStateFlow: StateFlow<MatchScreenStates> = _matchScreenStateFlow.asStateFlow()

    fun getMatch() {
        viewModelScope.launch {
            when (val matchResponse = footballRepository.getMatch()) {
                is Response.Success -> _matchScreenStateFlow.value =
                    MatchScreenStates.SuccessLoading(matchResponse.data)

                is Response.Error -> _matchScreenStateFlow.value =
                    MatchScreenStates.ErrorLoading(matchResponse.message)
            }
        }
    }

}