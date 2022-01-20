package com.example.strongerfootballapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.databinding.TeamActionsContainerBinding
import com.example.strongerfootballapp.domain.model.ActionTypes
import com.example.strongerfootballapp.domain.model.TeamAction

class TeamActionContainerView @JvmOverloads constructor(
    context: Context,
    defStyleAttrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : ConstraintLayout(context, defStyleAttrs, defStyleRes) {

    private val binding =
        TeamActionsContainerBinding.inflate(LayoutInflater.from(context), this)

    fun submitTeamActions(actionTime: String, teamActions: List<TeamAction>?){
        teamActions?.forEach {
            addActionToTeam(actionTime, it)
        }
    }

    private fun addActionToTeam(
        actionTime: String,
        teamAction: TeamAction
    ) {
        val actionView = if (teamAction.actionType == ActionTypes.SUBSTITUTION.actionId)
            SubstitutionTeamActionView(
                context,
                actionTime,
                teamAction.action.player1,
                teamAction.action.player2,
                teamAction.teamType
            )

        else
            RegularTeamActionView(
                context,
                teamAction.actionType,
                actionTime,
                teamAction.action.goalType,
                teamAction.action.player,
                teamAction.teamType
            )

        when(teamAction.teamType){
            FIRST_TEAM -> binding.firstTeamLinearLayout.addView(actionView)
            SECOND_TEAM -> binding.secondTeamLinearLayout.addView(actionView)
        }
    }

    companion object{
        private const val FIRST_TEAM = 1
        private const val SECOND_TEAM = 2
    }

}