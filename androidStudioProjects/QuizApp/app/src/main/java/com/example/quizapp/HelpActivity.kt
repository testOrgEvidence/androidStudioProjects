package com.example.quizapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        findViewById<TextView>(R.id.tvHelpTitle).text = "📖 How to Play"
        
        findViewById<TextView>(R.id.tvHelpContent).text = """
            Welcome to Quiz App!
            
            🎯 How to Play:
            1. Select your difficulty level (Easy, Medium, or Hard)
            2. Answer 5 random questions from various categories
            3. Each question has a time limit based on difficulty
            4. Build streaks by answering correctly in a row
            5. Track your high scores and statistics
            
            ⏱️ Time Limits:
            • Easy: 20 seconds per question
            • Medium: 15 seconds per question
            • Hard: 10 seconds per question
            
            🔥 Streaks:
            Get 3+ correct answers in a row to activate streak mode!
            
            🏆 Scoring:
            • 80%+ = Excellent
            • 50-79% = Pass
            • Below 50% = Keep practicing
            
            📊 Features:
            • View your statistics and game history
            • Check the leaderboard for past performances
            • Customize settings for sound and vibration
            
            Good luck and have fun! 🎉
        """.trimIndent()

        findViewById<Button>(R.id.btnBackHelp).setOnClickListener { finish() }
    }
}
