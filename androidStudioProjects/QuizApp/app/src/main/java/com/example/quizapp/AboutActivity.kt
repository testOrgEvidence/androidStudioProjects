package com.example.quizapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        findViewById<TextView>(R.id.tvAboutTitle).text = "ℹ️ About Quiz App"
        
        findViewById<TextView>(R.id.tvAboutContent).text = """
            Quiz App
            Version 1.0.0
            
            📱 A fun and educational quiz application to test your knowledge across multiple categories including Science, Geography, History, Math, and Literature.
            
            ✨ Features:
            • Multiple difficulty levels
            • Timed questions
            • Streak tracking
            • High score system
            • Detailed statistics
            • Leaderboard
            • Category-based questions
            
            👨‍💻 Developed by:
            Quiz App Team
            
            📧 Contact:
            support@quizapp.com
            
            🔗 Follow us:
            @QuizAppOfficial
            
            📄 License:
            MIT License © 2024
            
            🙏 Special Thanks:
            To all our beta testers and contributors who helped make this app better!
            
            Made with ❤️ using Kotlin & Android
        """.trimIndent()

        findViewById<Button>(R.id.btnRateApp).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store"))
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnShareApp).setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Check out Quiz App! Test your knowledge with fun quizzes.")
            }
            startActivity(Intent.createChooser(shareIntent, "Share Quiz App"))
        }

        findViewById<Button>(R.id.btnBackAbout).setOnClickListener { finish() }
    }
}
