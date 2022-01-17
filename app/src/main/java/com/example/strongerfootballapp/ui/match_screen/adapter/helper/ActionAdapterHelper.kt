package com.example.strongerfootballapp.ui.match_screen.adapter.helper

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.strongerfootballapp.model.TeamAction


interface ActionAdapterHelper {

    fun createActionView(
        context: Context,
        actionTime: String,
        teamActions: List<TeamAction>?,
        doRotate: Boolean,
        action: (ConstraintLayout) -> Unit
    )

}