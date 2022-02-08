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
import com.example.match_action_views.utils.Constants.MULTIPLIER_FOR_DPI
import com.example.match_action_views.utils.Constants.PADDING_0_DP
import com.example.match_action_views.utils.Constants.PADDING_10_DP
import com.example.match_action_views.utils.Constants.SECOND_TEAM

@SuppressLint("ViewConstructor")
class RegularTeamActionView(
    context: Context,
) : ConstraintLayout(context) {
    private val binding = RegularActionBinding.inflate(LayoutInflater.from(context), this)

    init {
        val scale = resources.displayMetrics.density
        val paddingTopAndBottom = (PADDING_10_DP * scale + MULTIPLIER_FOR_DPI).toInt()
        binding.root.setPadding(PADDING_0_DP, paddingTopAndBottom, PADDING_0_DP, paddingTopAndBottom)
    }

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

}