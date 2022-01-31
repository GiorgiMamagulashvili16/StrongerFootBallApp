package com.example.strongerfootballapp.presentation.match_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.domain.model.Score
import com.example.match_action_views.models.TeamActionUiModel
import com.example.strongerfootballapp.domain.model.Summary
import com.example.strongerfootballapp.domain.model.TeamAction
import com.example.match_action_views.views.HalfScoreView


interface ActionAdapterHelper {
    fun setFirstHalfScore(score: Score)

    fun setSecondHalfScore(score: Score)

    fun createActionView(
        context: Context,
        actionTime: String,
        teamActions: List<TeamAction>?,
        action: (ConstraintLayout) -> Unit
    )

    fun getHalfScoreView(
        context: Context, actionTime: String, data: List<Summary>
    ): HalfScoreView?

    fun mapTeamAction(actions: List<TeamAction>?): List<TeamActionUiModel>?
}