package com.example.quizapp.model

import com.example.quizapp.util.toPercentage

data class QuizStatistics(
    val totalGamesPlayed: Int = 0,
    val totalQuestionsAnswered: Int = 0,
    val totalCorrectAnswers: Int = 0,
    val totalWrongAnswers: Int = 0,
    val bestStreak: Int = 0,
    val averageScore: Int = 0,
    val easyHighScore: Int = 0,
    val mediumHighScore: Int = 0,
    val hardHighScore: Int = 0,
    val favoriteCategory: String = "N/A",
    val totalTimePlayed: Long = 0
) {
    fun getAccuracyPercentage(): Int {
        return if (totalQuestionsAnswered > 0) {
            totalCorrectAnswers.toPercentage(totalQuestionsAnswered)
        } else 0
    }

    fun getAverageQuestionsPerGame(): Int {
        return if (totalGamesPlayed > 0) {
            totalQuestionsAnswered / totalGamesPlayed
        } else 0
    }

    fun getTotalTimePlayedFormatted(): String {
        val hours = totalTimePlayed / 3600000
        val minutes = (totalTimePlayed % 3600000) / 60000
        return when {
            hours > 0 -> "${hours}h ${minutes}m"
            minutes > 0 -> "${minutes}m"
            else -> "< 1m"
        }
    }

    fun getPerformanceRating(): String {
        val accuracy = getAccuracyPercentage()
        return when {
            accuracy >= 90 -> "Expert 🏆"
            accuracy >= 75 -> "Advanced ⭐"
            accuracy >= 60 -> "Intermediate 📚"
            accuracy >= 40 -> "Beginner 🌱"
            else -> "Novice 🎯"
        }
    }

    fun getCategoryStats(): Map<String, Int> {
        return mapOf(
            "Science" to 0,
            "Geography" to 0,
            "History" to 0,
            "Math" to 0,
            "Literature" to 0
        )
    }
}

object StatisticsCalculator {
    
    fun calculateImprovement(oldStats: QuizStatistics, newStats: QuizStatistics): Int {
        val oldAccuracy = oldStats.getAccuracyPercentage()
        val newAccuracy = newStats.getAccuracyPercentage()
        return newAccuracy - oldAccuracy
    }

    fun predictNextScore(recentScores: List<Int>): Int {
        if (recentScores.isEmpty()) return 0
        if (recentScores.size == 1) return recentScores[0]
        
        val average = recentScores.average().toInt()
        val trend = recentScores.takeLast(3).average() - recentScores.take(3).average()
        
        return (average + trend).toInt().coerceIn(0, 100)
    }

    fun getMotivationalMessage(stats: QuizStatistics): String {
        val accuracy = stats.getAccuracyPercentage()
        val gamesPlayed = stats.totalGamesPlayed
        
        return when {
            gamesPlayed == 0 -> "Start your quiz journey today! 🚀"
            accuracy >= 90 -> "Outstanding performance! You're a quiz master! 🏆"
            accuracy >= 75 -> "Great job! Keep up the excellent work! ⭐"
            accuracy >= 60 -> "Good progress! You're improving! 📈"
            stats.bestStreak >= 5 -> "Amazing streak! You're on fire! 🔥"
            gamesPlayed >= 10 -> "Consistency is key! Keep practicing! 💪"
            else -> "Every quiz makes you smarter! Keep going! 🎯"
        }
    }
}
