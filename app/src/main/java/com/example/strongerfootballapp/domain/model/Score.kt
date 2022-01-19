package com.example.strongerfootballapp.domain.model

data class Score(private var firstTeamScore: Int = 0, private var secondTeamScore: Int = 0){

    override fun toString(): String {
        return SCORE_FORMAT.format(firstTeamScore, secondTeamScore)
    }

    fun increaseFirstTeamScore() = firstTeamScore++

    fun increaseSecondTeamScore() = secondTeamScore++

    companion object{
        private const val SCORE_FORMAT = "%d : %d"
    }
}
