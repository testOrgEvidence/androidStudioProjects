package com.example.quizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.util.toGrade
import com.example.quizapp.util.toPercentage

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)
        val difficulty = intent.getStringExtra("difficulty") ?: "Medium"
        val bestStreak = intent.getIntExtra("bestStreak", 0)

        val percentage = score.toPercentage(total)
        val grade = percentage.toGrade()

        findViewById<TextView>(R.id.tvResultTitle).text = "Quiz Complete!"
        findViewById<TextView>(R.id.tvGrade).text = grade
        findViewById<TextView>(R.id.tvResultScore).text = "$score / $total ($percentage%)"
        findViewById<TextView>(R.id.tvResultDifficulty).text = "Difficulty: $difficulty"
        findViewById<TextView>(R.id.tvResultStreak).text = if (bestStreak >= 2) "Best Streak: $bestStreak 🔥" else ""

        findViewById<Button>(R.id.btnPlayAgain).setOnClickListener { finish() }
    }
}
