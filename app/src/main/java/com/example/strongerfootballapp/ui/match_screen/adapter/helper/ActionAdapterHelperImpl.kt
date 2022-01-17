package com.example.footballapp.ui.details_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.footballapp.ui.views.RegularTeamActionView
import com.example.strongerfootballapp.model.TeamAction
import com.example.strongerfootballapp.ui.match_screen.adapter.helper.ActionAdapterHelper
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
            val actionView = if (it.actionType == 4)
                SubstitutionTeamActionView(context, actionTime, it.action.player1, it.action.player2, doRotate)
            else
                RegularTeamActionView(context, it.actionType, actionTime, it.action.goalType, it.action.player, doRotate)
            action(actionView)
        }
    }

}