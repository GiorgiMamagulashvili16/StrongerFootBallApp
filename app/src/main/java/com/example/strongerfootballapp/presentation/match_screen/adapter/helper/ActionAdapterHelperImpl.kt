package com.example.strongerfootballapp.presentation.match_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.domain.model.ActionTypes
import com.example.strongerfootballapp.domain.model.Summary
import com.example.strongerfootballapp.domain.model.TeamAction
import com.example.strongerfootballapp.domain.utils.Mapper
import com.example.strongerfootballapp.presentation.views.HalfScoreView
import com.example.strongerfootballapp.presentation.views.RegularTeamActionView
import com.example.strongerfootballapp.presentation.views.SubstitutionTeamActionView

class ActionAdapterHelperImpl: ActionAdapterHelper {

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
                SubstitutionTeamActionView(context, actionTime, it.action.player1, it.action.player2, it.teamType)
            else
                RegularTeamActionView(context, it.actionType, actionTime, it.action.goalType, it.action.player, it.teamType)
            action(actionView)
        }
    }

    override fun getHalfScoreView(context: Context, actionTime: String, data: List<Summary>): HalfScoreView? {
        val actionTimeValue = actionTime.toInt()
        val view = if (actionTimeValue <= HALF_DIVIDER_TIME && !hasFirstHalfStarted){
            val score = Mapper.mapMatchHalves(FIRST_HALF, data)
            hasFirstHalfStarted = true
            HalfScoreView(context, score =  score, half = FIRST_HALF)
        }else if (actionTimeValue > HALF_DIVIDER_TIME && !hasSecondHalfStarted){
            val score = Mapper.mapMatchHalves(SECOND_HALF, data)
            hasSecondHalfStarted = true
            HalfScoreView(context, score =  score, half = SECOND_HALF)
        }else null
        return view
    }

    companion object{
        private const val FIRST_HALF = 1
        private const val SECOND_HALF = 2
        private const val HALF_DIVIDER_TIME = 45
    }

}