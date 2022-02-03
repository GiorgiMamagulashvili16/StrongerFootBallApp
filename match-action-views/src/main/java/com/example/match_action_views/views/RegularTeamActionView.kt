package com.example.match_action_views.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.match_action_views.databinding.RegularActionBinding
import com.example.match_action_views.extensions.shortenLastName
import com.example.match_action_views.models.ActionTypeUIModel
import com.example.match_action_views.models.PlayerUIModel

@SuppressLint("ViewConstructor")
class RegularTeamActionView(
    context: Context,
) : ConstraintLayout(context) {
    private val binding = RegularActionBinding.inflate(LayoutInflater.from(context), this)

    fun setActionInfo(
        actionTextResource: Int,
        actionTime: String,
        actionType: ActionTypeUIModel?,
        teamType: Int
    ) {
        actionType?.let {
            with(binding) {
                val actionText = context.getString(actionType.actionTextRes)
                actionTextTextView.text =
                    context.getString(actionTextResource).format(actionTime, actionText)
                actionIconImageView.setImageResource(actionType.actionImageRes)
            }
            if (teamType == SECOND_TEAM) rotateView()
        }
    }

    fun setPlayerInfo(
        playerUIModel: PlayerUIModel?,
    ) {
        binding.playerNameTextView.text = playerUIModel?.playerName?.shortenLastName()
    }


    private fun rotateView() {
        with(binding) {
            root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }

    companion object {
        private const val SECOND_TEAM = 2
    }

}