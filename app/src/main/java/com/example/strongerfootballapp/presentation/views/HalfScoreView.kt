package com.example.strongerfootballapp.presentation.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.databinding.HalfScoreViewBinding
import com.example.strongerfootballapp.domain.model.Score
import com.example.strongerfootballapp.domain.model.Summary

@SuppressLint("ViewConstructor")
class HalfScoreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private var score: Score? = null,
    private val half: Int
): ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: HalfScoreViewBinding =
        HalfScoreViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val halfIndicatorText = when(half){
            FIRST_HALF -> context.getString(R.string.half_number).format(FIRST)
            else -> context.getString(R.string.half_number).format(SECOND)
        }
        binding.halfIndicatorTextView.text = halfIndicatorText
        binding.scoreIndicatorTextView.text = score.toString()
    }

    fun countAndSetHalfScore(summaries: List<Summary>) {
        val score = Score()
        summaries.forEach {
            val isHalfTimeCorrect = when(half){
                FIRST_HALF -> it.actionTime.toInt() <= HALVES_DIVIDER_TIME
                else -> it.actionTime.toInt() > HALVES_DIVIDER_TIME
            }
            if (isHalfTimeCorrect){
                it.countGoals(score)
            }
        }
        this.score = score
        binding.scoreIndicatorTextView.text = score.toString()
    }


    companion object{
        private const val HALVES_DIVIDER_TIME = 45
        private const val FIRST_HALF = 1
        private const val FIRST = "1st"
        private const val SECOND = "2nd"
    }

}