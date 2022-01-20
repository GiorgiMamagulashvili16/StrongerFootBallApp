package com.example.strongerfootballapp.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.R
import com.example.strongerfootballapp.databinding.HalfScoreViewBinding
import com.example.strongerfootballapp.domain.model.Score

class HalfScoreView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: HalfScoreViewBinding =
        HalfScoreViewBinding.inflate(LayoutInflater.from(context), this, true)

    fun showHalfScore(score: Score, half: Int){
        val halfIndicatorText = when(half){
            FIRST_HALF -> context.getString(R.string.half_number).format(FIRST)
            else -> context.getString(R.string.half_number).format(SECOND)
        }
        binding.halfIndicatorTextView.text = halfIndicatorText
        binding.scoreIndicatorTextView.text = score.toString()
    }


    companion object{
        private const val FIRST_HALF = 1
        private const val FIRST = "1st"
        private const val SECOND = "2nd"
    }

}