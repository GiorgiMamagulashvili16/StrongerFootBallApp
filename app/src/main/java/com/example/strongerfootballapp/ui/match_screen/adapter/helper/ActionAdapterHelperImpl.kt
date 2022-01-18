package com.example.strongerfootballapp.ui.match_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.ui.views.RegularTeamActionView
import com.example.strongerfootballapp.model.TeamAction
import com.example.strongerfootballapp.ui.views.SubstitutionTeamActionView

class ActionAdapterHelperImpl: ActionAdapterHelper {

    override fun createActionView(
        context: Context,
        actionTime: String,
        teamActions: List<TeamAction>?,
        doRotate: Boolean,
        action: (ConstraintLayout) -> Unit
    ) {
        teamActions?.forEach {
            val actionView = if (it.actionType == SUBSTITUTION_ACTION_TYPE)
                SubstitutionTeamActionView(context, actionTime, it.action.player1, it.action.player2, doRotate)
            else
                RegularTeamActionView(context, it.actionType, actionTime, it.action.goalType, it.action.player, doRotate)
            action(actionView)
        }
    }

    companion object{
        private const val SUBSTITUTION_ACTION_TYPE = 4
    }

}