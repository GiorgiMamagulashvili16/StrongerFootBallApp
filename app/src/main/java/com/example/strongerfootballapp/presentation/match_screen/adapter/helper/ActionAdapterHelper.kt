package com.example.strongerfootballapp.ui.match_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.model.Summary
import com.example.strongerfootballapp.model.TeamAction
import com.example.strongerfootballapp.ui.views.HalfScoreView


interface ActionAdapterHelper {

    fun createActionView(
        context: Context,
        actionTime: String,
        teamActions: List<TeamAction>?,
        action: (ConstraintLayout) -> Unit
    )

    fun showHalfScoreView(actionTime: String, halfScoreView: HalfScoreView, data: List<Summary>)
}