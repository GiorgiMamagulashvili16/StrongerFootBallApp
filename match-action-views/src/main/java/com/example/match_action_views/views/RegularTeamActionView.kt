package com.example.match_action_views.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.match_action_views.R
import com.example.match_action_views.databinding.RegularActionBinding
import com.example.match_action_views.extensions.shortenLastName
import com.example.match_action_views.models.ActionTypes
import com.example.match_action_views.models.PlayerUiModel

@SuppressLint("ViewConstructor")
class RegularTeamActionView(
    context: Context,
) : ConstraintLayout(context) {
    private val binding = RegularActionBinding.inflate(LayoutInflater.from(context), this)

    fun setActionInfo(
        actionTextResource: Int = R.string.action_text,
        actionTime: String,
        actionType: Int,
        goalType: Int?,
        teamType: Int
    ) {
        val mActionType = mapIntToActionType(actionType, goalType)
        with(binding) {
            mActionType?.let {
                val actionText = context.getString(it.actionTextRes)
                actionTextTextView.text =
                    context.getString(actionTextResource).format(actionTime, actionText)
                actionIconImageView.setImageResource(it.actionImageRes)
            }
        }
        if (teamType == SECOND_TEAM) rotateView()
    }

    fun setPlayerInfo(
        playerUiModel: PlayerUiModel?,
    ) {
        binding.playerNameTextView.text = playerUiModel?.playerName?.shortenLastName()
    }


    private fun rotateView() {
        with(binding) {
            root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }

    private fun mapIntToActionType(actionId: Int, goalType: Int?): ActionTypes? =
        ActionTypes.values().find { it.actionId == actionId && it.goalType == goalType }

    companion object {
        private const val SECOND_TEAM = 2
    }

}