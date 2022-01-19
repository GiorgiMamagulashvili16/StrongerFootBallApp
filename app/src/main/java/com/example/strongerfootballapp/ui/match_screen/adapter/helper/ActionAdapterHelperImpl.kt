package com.example.strongerfootballapp.ui.match_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import com.example.strongerfootballapp.model.ActionTypes
import com.example.strongerfootballapp.model.Summary
import com.example.strongerfootballapp.ui.views.RegularTeamActionView
import com.example.strongerfootballapp.model.TeamAction
import com.example.strongerfootballapp.ui.views.HalfScoreView
import com.example.strongerfootballapp.ui.views.SubstitutionTeamActionView
import com.example.strongerfootballapp.utils.Mapper

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

    override fun showHalfScoreView(actionTime: String, halfScoreView: HalfScoreView, data: List<Summary>){
        val actionTimeValue = actionTime.toInt()
        if (actionTimeValue <= HALF_DIVIDER_TIME && !hasFirstHalfStarted){
            val score = Mapper.mapMatchHalves(FIRST_HALF, data)
            halfScoreView.showHalfScore(score, FIRST_HALF)
            hasFirstHalfStarted = true
            halfScoreView.isGone = false
        }else if (actionTimeValue > HALF_DIVIDER_TIME && !hasSecondHalfStarted){
            val score = Mapper.mapMatchHalves(SECOND_HALF, data)
            halfScoreView.showHalfScore(score, SECOND_HALF)
            hasSecondHalfStarted = true
            halfScoreView.isGone = false
        }
    }

    companion object{
        private const val FIRST_HALF = 1
        private const val SECOND_HALF = 2
        private const val HALF_DIVIDER_TIME = 45
    }

}