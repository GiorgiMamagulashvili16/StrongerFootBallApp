package com.example.match_action_views.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.match_action_views.R
import com.example.match_action_views.databinding.SubstitutionActionBinding
import com.example.match_action_views.extensions.loadImage
import com.example.match_action_views.extensions.shortenLastName
import com.example.match_action_views.models.ActionTypes
import com.example.match_action_views.models.PlayerUiModel


@SuppressLint("ViewConstructor")
class SubstitutionTeamActionView(context: Context) : ConstraintLayout(context) {
    private val binding: SubstitutionActionBinding =
        SubstitutionActionBinding.inflate(LayoutInflater.from(context), this)

    fun setPlayerInfo(player1: PlayerUiModel?, player2: PlayerUiModel?, teamType: Int) {
        with(binding) {
            firstPlayerImage.loadImage(player1?.playerImage)
            secondPlayerImage.loadImage(player2?.playerImage)
            playerNameTextView.text = player1?.playerName?.shortenLastName()
            substitutedPlayerNameTextView.text = player2?.playerName?.shortenLastName()
            actionIconImageView.setImageResource(ActionTypes.SUBSTITUTION.actionImageRes)
        }
        if (teamType == SECOND_TEAM) rotateView()
    }

    fun setActionText(actionText: Int = R.string.action_text, actionTime: String) {
        binding.actionTextTextView.text =
            context.getString(actionText)
                .format(actionTime, ActionTypes.SUBSTITUTION.actionImageRes)
    }

    private fun rotateView() {
        with(binding) {
            root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }

    companion object {
        private const val REVERSED_SCALE = -1f
        private const val SECOND_TEAM = 2
    }
}