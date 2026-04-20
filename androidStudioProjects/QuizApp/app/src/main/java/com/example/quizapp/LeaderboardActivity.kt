package com.example.quizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.adapter.LeaderboardAdapter
import com.example.quizapp.util.ScoreManager
import com.example.quizapp.model.Difficulty

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var scoreManager: ScoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        scoreManager = ScoreManager(this)

        val tvTitle = findViewById<TextView>(R.id.tvLeaderboardTitle)
        val recyclerView = findViewById<RecyclerView>(R.id.rvLeaderboard)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val tvSummary = findViewById<TextView>(R.id.tvSummary)

        tvTitle.text = "🏆 Leaderboard"

        val entries = scoreManager.getHistory()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LeaderboardAdapter(entries)

        val totalGames = scoreManager.getTotalGamesPlayed()
        val totalCorrect = scoreManager.getTotalCorrectAnswers()
        val avgScore = if (totalGames > 0) totalCorrect / totalGames else 0
        tvSummary.text = "Total Games: $totalGames | Avg Correct/Game: $avgScore | Best Streak: ${scoreManager.getBestStreak()}"

        btnBack.setOnClickListener { finish() }
    }
}
