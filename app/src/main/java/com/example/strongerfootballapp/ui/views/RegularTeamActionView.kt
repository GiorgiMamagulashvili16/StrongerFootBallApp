package com.example.footballapp.ui.views

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
    rotate: Boolean = false
) : ConstraintLayout(context) {
    private val binding = RegularActionBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        val mActionType = Mapper.mapIntToActionType(actionType, goalType)
        with(binding){
            mActionType?.let {
                val actionText = context.getString(it.actionTextRes)
                firstTeamActionText.text = context.getString(R.string.action_text).format(actionTime, actionText)
                firstTeamPlayerName.text = player?.playerName?.shortenLastName()
                firstTeamFirstPlayerImage.setImageResource(R.mipmap.ic_launcher)
                firstTeamActionIcon.setImageResource(it.actionImageRes)
            }
        }
        if (rotate) rotateView()
    }

    private fun rotateView(){
        with(binding){
            root.scaleX = -1f
            firstTeamActionText.scaleX = REVERSED_SCALE
            firstTeamPlayerName.scaleX = REVERSED_SCALE
        }
    }

    companion object{
        private const val REVERSED_SCALE = -1f
    }

}