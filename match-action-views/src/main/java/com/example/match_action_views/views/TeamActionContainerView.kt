package com.example.match_action_views.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.match_action_views.databinding.TeamActionsContainerBinding
import com.example.match_action_views.models.ActionTypes
import com.example.match_action_views.models.TeamActionUiModel

class TeamActionContainerView @JvmOverloads constructor(
    context: Context,
    defStyleAttrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : ConstraintLayout(context, defStyleAttrs, defStyleRes) {

    private val binding =
        TeamActionsContainerBinding.inflate(LayoutInflater.from(context), this)

    fun submitTeamActions(actionTime: String, teamActions: List<TeamActionUiModel>?) {
        teamActions?.forEach {
            addActionToTeam(actionTime, it)
        }
    }

    private fun addActionToTeam(
        actionTime: String,
        teamAction: TeamActionUiModel
    ) {
        val actionView = if (teamAction.actionType == ActionTypes.SUBSTITUTION.actionId)
            SubstitutionTeamActionView(
                context,
            ).apply {
                setPlayerInfo(
                    teamAction.action.player1,
                    teamAction.action.player2,
                    teamAction.teamType
                )
                setActionText(actionTime = actionTime)
            }
        else
            RegularTeamActionView(
                context,
            ).apply {
                setPlayerInfo(
                    teamAction.action.player,
                )
                setActionInfo(
                    actionTime = actionTime,
                    goalType = teamAction.action.goalType,
                    teamType = teamAction.teamType,
                    actionType = teamAction.actionType,
                )
            }

        when (teamAction.teamType) {
            FIRST_TEAM -> binding.firstTeamLinearLayout.addView(actionView)
            SECOND_TEAM -> binding.secondTeamLinearLayout.addView(actionView)
        }
    }

    companion object {
        private const val FIRST_TEAM = 1
        private const val SECOND_TEAM = 2
    }
}