package com.example.match_action_views.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.match_action_views.databinding.SubstitutionActionBinding
import com.example.match_action_views.extensions.loadImage
import com.example.match_action_views.extensions.shortenLastName
import com.example.match_action_views.models.ActionTypeUIModel
import com.example.match_action_views.models.PlayerUIModel
import com.example.match_action_views.utils.Constants.MULTIPLIER_FOR_DPI
import com.example.match_action_views.utils.Constants.PADDING_0_DP
import com.example.match_action_views.utils.Constants.PADDING_10_DP
import com.example.match_action_views.utils.Constants.SECOND_TEAM


@SuppressLint("ViewConstructor")
class SubstitutionTeamActionView(context: Context) : ConstraintLayout(context) {
    private val binding: SubstitutionActionBinding =
        SubstitutionActionBinding.inflate(LayoutInflater.from(context), this)

    init {
        val scale = resources.displayMetrics.density
        val paddingTopAndBottom = (PADDING_10_DP * scale + MULTIPLIER_FOR_DPI).toInt()
        binding.root.setPadding(PADDING_0_DP,paddingTopAndBottom,PADDING_0_DP,paddingTopAndBottom)
    }

    fun setPlayerInfo(player1: PlayerUIModel?, player2: PlayerUIModel?, teamType: Int) {
        with(binding) {
            firstPlayerImage.loadImage(player1?.playerImage)
            secondPlayerImage.loadImage(player2?.playerImage)
            playerNameTextView.text = player1?.playerName?.shortenLastName()
            substitutedPlayerNameTextView.text = player2?.playerName?.shortenLastName()
        }
        if (teamType == SECOND_TEAM) rotateView()
    }

    fun setActionText(actionText: Int, actionTime: String, actionType: ActionTypeUIModel) {
        val actionTimeText = context.getString(actionType.actionTextRes)
        binding.actionTextTextView.text =
            context.getString(actionText)
                .format(actionTime, actionTimeText)
    }

    private fun rotateView() {
        with(binding) {
            root.layoutDirection = View.LAYOUT_DIRECTION_RTL
        }
    }


}