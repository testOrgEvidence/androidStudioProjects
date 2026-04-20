package com.example.quizapp.util

import android.os.CountDownTimer

class QuizTimer(
    private val durationMs: Long,
    private val onTick: (secondsLeft: Long) -> Unit,
    private val onTimeUp: () -> Unit
) {
    private var timer: CountDownTimer? = null
    private var isRunning = false

    fun start() {
        cancel()
        timer = object : CountDownTimer(durationMs, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                isRunning = true
                onTick(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                isRunning = false
                onTimeUp()
            }
        }.start()
    }

    fun cancel() {
        timer?.cancel()
        isRunning = false
    }

    fun isActive(): Boolean = isRunning
}
