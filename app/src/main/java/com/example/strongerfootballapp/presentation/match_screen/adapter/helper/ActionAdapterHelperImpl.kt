package com.example.strongerfootballapp.presentation.match_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.domain.model.Score
import com.example.match_action_views.models.TeamActionUiModel
import com.example.match_action_views.views.HalfScoreView
import com.example.match_action_views.views.RegularTeamActionView
import com.example.match_action_views.views.SubstitutionTeamActionView
import com.example.strongerfootballapp.domain.mappers.TeamActionUiModelMapper
import com.example.strongerfootballapp.domain.model.ActionTypes
import com.example.strongerfootballapp.domain.model.Summary
import com.example.strongerfootballapp.domain.model.TeamAction

class ActionAdapterHelperImpl(
    private val teamActionUiModelMapper: TeamActionUiModelMapper,
) : ActionAdapterHelper {

    private var hasFirstHalfStarted = false
    private var hasSecondHalfStarted = false

    private var firstHalfScore: Score? = null
    private var secondHalfScore: Score? = null

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
        val view = if (actionTimeValue <= HALF_DIVIDER_TIME && !hasFirstHalfStarted) {

            hasFirstHalfStarted = true
            HalfScoreView(context).apply {
                setHalfIndicator(FIRST_HALF)
                setScore(firstHalfScore?.getFirstTeamScore(), firstHalfScore?.getSecondTeamScore())
            }

        } else if (actionTimeValue > HALF_DIVIDER_TIME && !hasSecondHalfStarted) {

            hasSecondHalfStarted = true
            HalfScoreView(context).apply {
                setHalfIndicator(SECOND_HALF)
                setScore(secondHalfScore?.getFirstTeamScore(), secondHalfScore?.getSecondTeamScore())
            }

        } else null
        return view
    }

    override fun mapTeamAction(actions: List<TeamAction>?): List<TeamActionUiModel>? {
        return teamActionUiModelMapper.mapToNullableList(actions)
    }

    companion object {
        private const val HALF_DIVIDER_TIME = 45
        private const val FIRST_HALF = "1st half"
        private const val SECOND_HALF = "2nd half"
    }

}