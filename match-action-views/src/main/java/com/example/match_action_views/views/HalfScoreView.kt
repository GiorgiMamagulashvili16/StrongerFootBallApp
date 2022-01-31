package com.example.match_action_views.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.match_action_views.databinding.HalfScoreViewBinding
import com.example.match_action_views.models.Score

@SuppressLint("ViewConstructor")
class  HalfScoreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: HalfScoreViewBinding =
        HalfScoreViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun setScore(score: Score){
        binding.scoreIndicatorTextView.text = score.toString()
    }

    fun setHalfIndicator(indicator: String){
        binding.halfIndicatorTextView.text = indicator
    }

}