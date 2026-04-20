package com.example.quizapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        prefs = getSharedPreferences("quiz_settings", MODE_PRIVATE)

        val switchSound = findViewById<Switch>(R.id.switchSound)
        val switchVibration = findViewById<Switch>(R.id.switchVibration)
        val switchDarkMode = findViewById<Switch>(R.id.switchDarkMode)
        val btnSave = findViewById<Button>(R.id.btnSaveSettings)
        val btnBack = findViewById<Button>(R.id.btnBackSettings)

        switchSound.isChecked = prefs.getBoolean("sound_enabled", true)
        switchVibration.isChecked = prefs.getBoolean("vibration_enabled", true)
        switchDarkMode.isChecked = prefs.getBoolean("dark_mode", false)

        btnSave.setOnClickListener {
            prefs.edit().apply {
                putBoolean("sound_enabled", switchSound.isChecked)
                putBoolean("vibration_enabled", switchVibration.isChecked)
                putBoolean("dark_mode", switchDarkMode.isChecked)
                apply()
            }
            finish()
        }

        btnBack.setOnClickListener { finish() }
    }
}
