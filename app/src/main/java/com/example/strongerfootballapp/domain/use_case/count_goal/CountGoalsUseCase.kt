package com.example.strongerfootballapp.domain.use_case.count_goal

import com.example.strongerfootballapp.domain.model.Score
import com.example.strongerfootballapp.domain.model.Summary

interface CountGoalsUseCase {

    fun countGoals(half: Int, summaries: List<Summary>): Score

}