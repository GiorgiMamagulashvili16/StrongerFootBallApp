package com.example.strongerfootballapp.presentation.match_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.domain.model.Score
import com.example.match_action_views.models.TeamActionUIModel
import com.example.match_action_views.views.HalfScoreView
import com.example.match_action_views.views.RegularTeamActionView
import com.example.match_action_views.views.SubstitutionTeamActionView
import com.example.strongerfootballapp.domain.mappers.TeamActionUIModelMapper
import com.example.strongerfootballapp.domain.model.ActionTypes
import com.example.strongerfootballapp.domain.model.Summary
import com.example.strongerfootballapp.domain.model.TeamAction

class ActionAdapterHelperImpl(
    private val teamActionUIModelMapper: TeamActionUIModelMapper,
) : ActionAdapterHelper {

    private var firstHalfScore: Score? = null
    private var secondHalfScore: Score? = null

    private val matchHalf = MatchHalf(1)

    override fun setFirstHalfScore(score: Score) {
        firstHalfScore = score
    }

    override fun setSecondHalfScore(score: Score) {
        secondHalfScore = score
    }

    override fun createActionView(
        context: Context,
        actionTime: String,
        teamActions: List<TeamAction>?,
        action: (ConstraintLayout) -> Unit
    ) {
        teamActions?.forEach {
            val actionView = if (it.actionType == ActionTypes.SUBSTITUTION.actionId)
                SubstitutionTeamActionView(context)
            else
                RegularTeamActionView(context)
            action(actionView)
        }
    }

    override fun getHalfScoreView(
        context: Context,
        actionTime: String,
        data: List<Summary>,
    ): HalfScoreView? {
        val actionTimeValue = actionTime.toInt()
        val view = if (!matchHalf.hasHalfStarted(actionTimeValue)) {
            val score = matchHalf.determineCorrespondingScore(firstHalfScore, secondHalfScore)
            HalfScoreView(context).apply {
                setScore(score!!.getFormattedScore())
                setHalfIndicator(context.getString(matchHalf.getHalfIndicator()))
            }
        } else null
        return view
    }

    override fun mapTeamAction(actions: List<TeamAction>?): List<TeamActionUIModel>? {
        return teamActionUIModelMapper.mapToNullableList(actions)
    }

}