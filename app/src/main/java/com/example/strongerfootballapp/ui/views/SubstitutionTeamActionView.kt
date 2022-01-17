package com.example.strongerfootballapp.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.databinding.SubstitutionActionBinding
import com.example.strongerfootballapp.model.ActionTypes
import com.example.strongerfootballapp.model.Player
import com.example.strongerfootballapp.utils.extensions.loadImage
import com.example.strongerfootballapp.utils.extensions.shortenLastName

@SuppressLint("ViewConstructor")
class SubstitutionTeamActionView(
    context: Context,
    actionTime: String,
    player1: Player?,
    player2: Player?,
    rotate: Boolean = false
) : ConstraintLayout(context) {
    private val binding: SubstitutionActionBinding =
        SubstitutionActionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(binding) {
            firstTeamActionText.text =
                context.getString(R.string.action_text)
                    .format(actionTime, ActionTypes.SUBSTITUTION.actionImageRes)
            firstPlayerImage.loadImage(player1?.playerImage)
            secondPlayerImage.loadImage(player2?.playerImage)
            firstTeamPlayerName.text = player1?.playerName?.shortenLastName()
            firstTeamSubstitutedPlayerName.text = player2?.playerName?.shortenLastName()
            firstTeamActionIcon.setImageResource(ActionTypes.SUBSTITUTION.actionImageRes)
        }
        if (rotate) rotateView()
    }

    private fun rotateView() {
        with(binding) {
            root.scaleX = -1f
            firstTeamActionText.scaleX = -1f
            firstTeamSubstitutedPlayerName.scaleX = -1f
            firstTeamPlayerName.scaleX = -1f
        }
    }
}