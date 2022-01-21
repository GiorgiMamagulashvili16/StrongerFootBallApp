package com.example.strongerfootballapp.presentation.match_screen

import android.util.Log.d
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strongerfootballapp.domain.use_case.GetMatchUseCase
import com.example.strongerfootballapp.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MatchViewModel(private val getMatchUseCase: GetMatchUseCase) : ViewModel() {
    private val _matchScreenStateFlow = MutableStateFlow<MatchScreenStates>(MatchScreenStates.Idle)
    val matchScreenStateFlow: StateFlow<MatchScreenStates> = _matchScreenStateFlow.asStateFlow()

    fun getMatch() {
        viewModelScope.launch {
            d("MatchResponse","${getMatchUseCase.getMatch()}")
            when (val matchResponse = getMatchUseCase.getMatch()) {
                is Resource.Success -> _matchScreenStateFlow.value =
                    MatchScreenStates.SuccessLoading(matchResponse.data)

                is Resource.Error -> _matchScreenStateFlow.value =
                    MatchScreenStates.ErrorLoading(matchResponse.message)
            }
        }
    }

}