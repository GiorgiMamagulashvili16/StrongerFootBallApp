package com.example.strongerfootballapp.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.databinding.RegularActionBinding
import com.example.strongerfootballapp.model.Player
import com.example.strongerfootballapp.utils.Mapper
import com.example.strongerfootballapp.utils.extensions.shortenLastName

@SuppressLint("ViewConstructor")
class RegularTeamActionView(
    context: Context,
    actionType: Int,
    actionTime: String,
    goalType: Int?,
    player: Player?,
    teamType: Int
) : ConstraintLayout(context) {
    private val binding = RegularActionBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        val mActionType = Mapper.mapIntToActionType(actionType, goalType)
        with(binding){
            mActionType?.let {
                val actionText = context.getString(it.actionTextRes)
                actionTextTextView.text = context.getString(R.string.action_text).format(actionTime, actionText)
                playerNameTextView.text = player?.playerName?.shortenLastName()
                playerImage.setImageResource(R.mipmap.ic_launcher)
                actionIconImageView.setImageResource(it.actionImageRes)
            }
        }
        if (teamType == SECOND_TEAM) rotateView()
    }

    private fun rotateView(){
        with(binding){
            root.scaleX = REVERSED_SCALE
            textGroup.scaleX = REVERSED_SCALE
        }
    }

    companion object{
        private const val REVERSED_SCALE = -1f
        private const val SECOND_TEAM = 2
    }

}