package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val splashDuration = 2500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val tvAppName = findViewById<TextView>(R.id.tvSplashAppName)
        val tvTagline = findViewById<TextView>(R.id.tvSplashTagline)
        val imgLogo = findViewById<ImageView>(R.id.imgSplashLogo)

        tvAppName.text = "🧠 Quiz App"
        tvTagline.text = "Test Your Knowledge"

        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        tvAppName.startAnimation(fadeIn)
        tvTagline.startAnimation(fadeIn)
        imgLogo.startAnimation(fadeIn)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, splashDuration)
    }

    override fun onBackPressed() {
        // Disable back button on splash screen
    }
}
