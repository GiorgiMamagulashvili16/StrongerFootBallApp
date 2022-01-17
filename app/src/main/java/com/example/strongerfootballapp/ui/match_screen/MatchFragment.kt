package com.example.strongerfootballapp.ui.match_screen

import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.databinding.MatchFragmentBinding
import com.example.strongerfootballapp.model.Match
import com.example.strongerfootballapp.model.Score
import com.example.strongerfootballapp.ui.base.BaseFragment
import com.example.strongerfootballapp.ui.base.Inflate
import com.example.strongerfootballapp.utils.Mapper
import com.example.strongerfootballapp.utils.extensions.loadImage
import com.example.strongerfootballapp.utils.extensions.makeToast
import com.example.strongerfootballapp.utils.extensions.toFormattedDate
import kotlinx.coroutines.flow.collect
import org.koin.core.component.get
import kotlin.reflect.KClass

class MatchFragment : BaseFragment<MatchFragmentBinding,MatchViewModel>() {
    override val viewModelClass: KClass<MatchViewModel>
        get() = MatchViewModel::class

    override fun inflateFragment(): Inflate<MatchFragmentBinding> {
       return MatchFragmentBinding::inflate
    }

    override fun onBindViewModel(viewModel: MatchViewModel) {
        viewModel.getMatch()
    }

    private fun initUI(match: Match){
        val score = Score(match.match.team1.score, match.match.team2.score)
        with(binding){
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
            scoreIndicatorTextView.text =
                Mapper.mapMatchHalves(1, match.match.matchSummary.summaries).toString()
        }
    }

    override suspend fun observeStates(viewModel: MatchViewModel) {
        viewModel.matchScreenStateFlow.collect{
            when (it){
                is MatchScreenStates.SuccessLoading -> initUI(it.data)
                is MatchScreenStates.ErrorLoading -> makeToast(it.message)
            }
        }
    }
}