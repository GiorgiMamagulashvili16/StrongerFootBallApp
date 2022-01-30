package com.example.strongerfootballapp.domain.use_case.count_goal

import com.example.match_action_views.models.Score
import com.example.strongerfootballapp.domain.model.Summary

interface CountGoalsUseCase {

    fun countGoals(half: Int, summaries: List<Summary>): Score

}