package com.example.match_action_views.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.match_action_views.databinding.HalfScoreViewBinding

@SuppressLint("ViewConstructor")
class  HalfScoreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    firstTeamScore: Int?,
    secondTeamScore: Int?
): ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: HalfScoreViewBinding =
        HalfScoreViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.scoreIndicatorTextView.text = SCORE_FORMAT.format(firstTeamScore?:0, secondTeamScore?:0)
    }

    fun setHalfIndicator(indicator: String){
        binding.halfIndicatorTextView.text = indicator
    }

    companion object{
        private const val SCORE_FORMAT = "%d : %d"
    }

}