package com.example.quizapp.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Int.toPercentage(total: Int): Int {
    if (total == 0) return 0
    return (this * 100) / total
}

fun Int.toGrade(): String {
    return when {
        this >= 90 -> "A+"
        this >= 80 -> "A"
        this >= 70 -> "B"
        this >= 60 -> "C"
        this >= 50 -> "D"
        else -> "F"
    }
}
