package com.example.match_action_views.models

data class Score(private var firstTeamScore: Int = 0, private var secondTeamScore: Int = 0){

    override fun toString(): String {
        return SCORE_FORMAT.format(firstTeamScore, secondTeamScore)
    }

    fun increaseFirstTeamScore() = firstTeamScore++

    fun increaseSecondTeamScore() = secondTeamScore++

    private fun getFirstTeamScore() = firstTeamScore

    private fun getSecondTeamScore() = secondTeamScore

    operator fun plus(other: Score){
        firstTeamScore += other.getFirstTeamScore()
        secondTeamScore += other.getSecondTeamScore()
    }

    companion object{
        private const val SCORE_FORMAT = "%d : %d"
    }
}
