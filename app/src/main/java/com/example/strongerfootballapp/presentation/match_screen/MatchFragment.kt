package com.example.strongerfootballapp.presentation.match_screen

import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.databinding.MatchFragmentBinding
import com.example.strongerfootballapp.domain.model.Match
import com.example.strongerfootballapp.domain.model.Score
import com.example.strongerfootballapp.domain.utils.extensions.*
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
        observeMatchTime(viewModel)
        observeFirstTeamBallPossession(viewModel)
        observeSecondTeamBallPossession(viewModel)
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

    private fun initUI(match: Match) {
        val score = Score(
            match.match.team1.score,
            match.match.team2.score
        )
        with(binding) {
            with(match.match) {
                setTeamsInfo(team1.teamImage, team2.teamImage, team1.teamName, team2.teamName)
                setMatchStatistics(score, team1.ballPosition)
                setMatchDate(matchDate, stadiumAdress)
            }
            matchEventsRecyclerView.adapter = adapter
        }
    }

    private fun observeMatchTime(matchViewModel: MatchViewModel) {
        launchLifeCycleScope {
            matchViewModel.matchTimeFlow.collect {
                it?.let {
                    binding.timeIndicatorTextView.text = it
                }
            }
        }
    }

    private fun observeFirstTeamBallPossession(matchViewModel: MatchViewModel) {
        launchLifeCycleScope {
            matchViewModel.firstTeamBallPossession.collect {
                it?.let {
                    binding.firstTeamBallPossessionTextView.text = it
                }
            }
        }
    }

    private fun observeSecondTeamBallPossession(matchViewModel: MatchViewModel) {
        launchLifeCycleScope {
            matchViewModel.secondTeamBallPossession.collect {
                it?.let {
                    binding.secondTeamBallPossessionTextView.text = it
                }
            }
        }
    }

    private fun setTeamsInfo(
        firstTeamImage: String,
        secondTeamImage: String,
        firstTeamName: String,
        secondTeamName: String
    ) {
        with(binding) {
            teamOneLogoImageView.loadImage(firstTeamImage)
            teamOneNameTextView.text = firstTeamName
            teamTwoLogoImageView.loadImage(secondTeamImage)
            teamTwoNameTextView.text = secondTeamName
        }
    }

    private fun setMatchStatistics(
        score: Score,
        firstTeamBallPossession: Int,
    ) {
        with(binding) {
            matchScoreTextView.text = score.toString()
            ballPossessionProgressBar.progress = firstTeamBallPossession
        }
    }

    private fun setMatchDate(date: Long, stadiumName: String) {
        binding.eventDateAndStadiumName.setTextWithMultipleColor(
            mutableListOf(date.toFormattedDate(), stadiumName), mutableListOf(
                R.color.app_main_color, R.color.gray
            )
        )
    }

    companion object {
        private const val FIRST_HALF = 1
        private const val SECOND_HALF = 2
    }
}