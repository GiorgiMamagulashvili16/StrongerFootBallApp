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
): ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: HalfScoreViewBinding =
        HalfScoreViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun setHalfIndicator(indicator: String){
        binding.halfIndicatorTextView.text = indicator
    }

    fun setScore(score: String){
        binding.scoreIndicatorTextView.text = score
    }
}