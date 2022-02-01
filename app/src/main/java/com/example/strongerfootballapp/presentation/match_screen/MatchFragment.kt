package com.example.strongerfootballapp.presentation.match_screen

import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.databinding.MatchFragmentBinding
import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.model.Score
import com.example.strongerfootballapp.domain.utils.extensions.loadImage
import com.example.strongerfootballapp.domain.utils.extensions.makeToast
import com.example.strongerfootballapp.domain.utils.extensions.toFormattedDate
import com.example.strongerfootballapp.presentation.base.BaseFragment
import com.example.strongerfootballapp.presentation.base.Inflate
import com.example.strongerfootballapp.presentation.match_screen.adapter.ActionsAdapter
import com.example.strongerfootballapp.presentation.match_screen.adapter.helper.ActionAdapterHelper
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import kotlin.reflect.KClass

class MatchFragment : BaseFragment<MatchFragmentBinding, MatchViewModel>() {
    override val viewModelClass: KClass<MatchViewModel>
        get() = MatchViewModel::class

    private val helper: ActionAdapterHelper by inject()
    private val adapter by lazy { ActionsAdapter(helper) }

    override fun inflateFragment(): Inflate<MatchFragmentBinding> {
        return MatchFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: MatchViewModel) {
        viewModel.getMatch()

    }

    private fun initUI(match: Match) {
        val score = Score(
            match.match.team1.score,
            match.match.team2.score
        )
        with(binding) {
            eventDateAndStadiumName.text = getString(R.string.date_and_stadium_name)
                .format(match.match.matchDate.toFormattedDate(), match.match.stadiumAdress)
            teamOneLogoImageView.loadImage(match.match.team1.teamImage)
            teamOneNameTextView.text = match.match.team1.teamName
            teamTwoLogoImageView.loadImage(match.match.team2.teamImage)
            teamTwoNameTextView.text = match.match.team2.teamName
            matchScoreTextView.text = score.toString()
            timeIndicatorTextView.text = getString(R.string.time)
                .format(match.match.matchTime)
            firstTeamBallPossessionTextView.text = getString(R.string.percent)
                .format(match.match.team1.ballPosition)
            secondTeamBallPossessionTextView.text = getString(R.string.percent)
                .format(match.match.team2.ballPosition)
            ballPossessionProgressBar.progress = match.match.team1.ballPosition
            matchEventsRecyclerView.adapter = adapter
        }
    }

    override suspend fun observeStates(viewModel: MatchViewModel) {
        viewModel.matchScreenStateFlow.collect {
            when (it) {
                is MatchScreenStates.SuccessLoading -> {
                    val summaries = it.data.match.matchSummary.summaries
                    helper.setFirstHalfScore(viewModel.getHalfScore(FIRST_HALF, summaries))
                    helper.setSecondHalfScore(viewModel.getHalfScore(SECOND_HALF, summaries))
                    adapter.submitList(summaries)
                    initUI(it.data)
                }
                is MatchScreenStates.ErrorLoading -> makeToast(it.message)
            }
        }
    }

    companion object{
        private const val FIRST_HALF = 1
        private const val SECOND_HALF = 2
    }
}