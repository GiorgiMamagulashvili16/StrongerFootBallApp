package com.example.strongerfootballapp.presentation.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.databinding.SubstitutionActionBinding
import com.example.strongerfootballapp.domain.model.ActionTypes
import com.example.strongerfootballapp.domain.model.Player
import com.example.strongerfootballapp.domain.utils.extensions.loadImage
import com.example.strongerfootballapp.domain.utils.extensions.shortenLastName

@SuppressLint("ViewConstructor")
class SubstitutionTeamActionView(
    context: Context,
    actionTime: String,
    player1: Player?,
    player2: Player?,
    teamType: Int
) : ConstraintLayout(context) {
    private val binding: SubstitutionActionBinding =
        SubstitutionActionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(binding) {
            actionTextTextView.text =
                context.getString(R.string.action_text)
                    .format(actionTime, ActionTypes.SUBSTITUTION.actionImageRes)
            firstPlayerImage.loadImage(player1?.playerImage)
            secondPlayerImage.loadImage(player2?.playerImage)
            playerNameTextView.text = player1?.playerName?.shortenLastName()
            substitutedPlayerNameTextView.text = player2?.playerName?.shortenLastName()
            actionIconImageView.setImageResource(ActionTypes.SUBSTITUTION.actionImageRes)
        }
        if (teamType == SECOND_TEAM) rotateView()
    }

    private fun rotateView() {
        with(binding) {
            root.scaleX = REVERSED_SCALE
            textGroup.scaleX = REVERSED_SCALE
        }
    }

    companion object{
        private const val REVERSED_SCALE = -1f
        private const val SECOND_TEAM = 2
    }
}