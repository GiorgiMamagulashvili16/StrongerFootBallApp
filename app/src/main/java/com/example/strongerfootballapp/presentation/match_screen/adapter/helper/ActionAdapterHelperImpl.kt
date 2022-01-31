package com.example.strongerfootballapp.presentation.match_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.match_action_views.views.HalfScoreView
import com.example.match_action_views.views.RegularTeamActionView
import com.example.match_action_views.views.SubstitutionTeamActionView
import com.example.strongerfootballapp.domain.mappers.TeamActionUiModelMapper
import com.example.strongerfootballapp.domain.model.ActionTypes
import com.example.strongerfootballapp.domain.model.Summary
import com.example.strongerfootballapp.domain.model.TeamAction

class ActionAdapterHelperImpl(override val teamActionUiModelMapper: TeamActionUiModelMapper) :
    ActionAdapterHelper {

    private var hasFirstHalfStarted = false
    private var hasSecondHalfStarted = false

    override fun createActionView(
        context: Context,
        actionTime: String,
        teamActions: List<TeamAction>?,
        action: (ConstraintLayout) -> Unit
    ) {
        teamActions?.forEach {
            val actionView = if (it.actionType == ActionTypes.SUBSTITUTION.actionId)
                SubstitutionTeamActionView(context)
            else
                RegularTeamActionView(context)
            action(actionView)
        }
    }

    override fun getHalfScoreView(
        context: Context,
        actionTime: String,
        data: List<Summary>
    ): HalfScoreView? {
        val actionTimeValue = actionTime.toInt()
        val view = if (actionTimeValue <= HALF_DIVIDER_TIME && !hasFirstHalfStarted) {
            hasFirstHalfStarted = true
            HalfScoreView(context)
        } else if (actionTimeValue > HALF_DIVIDER_TIME && !hasSecondHalfStarted) {
            hasSecondHalfStarted = true
            HalfScoreView(context)
        } else null
        return view
    }

    companion object {
        private const val FIRST_HALF = 1
        private const val SECOND_HALF = 2
        private const val HALF_DIVIDER_TIME = 45
    }

}