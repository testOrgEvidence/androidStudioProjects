package com.example.quizapp.util

import android.content.Context
import android.content.SharedPreferences
import com.example.quizapp.model.Difficulty

class ScoreManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("quiz_scores", Context.MODE_PRIVATE)

    fun saveScore(difficulty: Difficulty, score: Int, total: Int) {
        val key = "high_score_${difficulty.name}"
        val percentage = (score * 100) / total
        val currentBest = getHighScore(difficulty)
        if (percentage > currentBest) {
            prefs.edit().putInt(key, percentage).apply()
        }
        incrementGamesPlayed()
        addToTotalCorrect(score)
    }

    fun getHighScore(difficulty: Difficulty): Int {
        return prefs.getInt("high_score_${difficulty.name}", 0)
    }

    fun getTotalGamesPlayed(): Int {
        return prefs.getInt("games_played", 0)
    }

    fun getTotalCorrectAnswers(): Int {
        return prefs.getInt("total_correct", 0)
    }

    fun getBestStreak(): Int {
        return prefs.getInt("best_streak", 0)
    }

    fun saveBestStreak(streak: Int) {
        val current = getBestStreak()
        if (streak > current) {
            prefs.edit().putInt("best_streak", streak).apply()
        }
    }

    fun resetAllScores() {
        prefs.edit().clear().apply()
    }

    private fun incrementGamesPlayed() {
        val current = getTotalGamesPlayed()
        prefs.edit().putInt("games_played", current + 1).apply()
    }

    private fun addToTotalCorrect(correct: Int) {
        val current = getTotalCorrectAnswers()
        prefs.edit().putInt("total_correct", current + correct).apply()
    }
}
