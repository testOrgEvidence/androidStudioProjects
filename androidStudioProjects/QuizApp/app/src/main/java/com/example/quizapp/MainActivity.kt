package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val questions = listOf(
        Question("What is the capital of France?", listOf("Berlin", "Paris", "Madrid", "Rome"), 1),
        Question("Which planet is known as the Red Planet?", listOf("Venus", "Jupiter", "Mars", "Saturn"), 2),
        Question("What is 7 x 8?", listOf("54", "56", "58", "64"), 1),
        Question("Who wrote Romeo and Juliet?", listOf("Dickens", "Shakespeare", "Austen", "Twain"), 1),
        Question("What is the largest ocean?", listOf("Atlantic", "Indian", "Arctic", "Pacific"), 3)
    )

    private var currentIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val rbOption1 = findViewById<RadioButton>(R.id.rbOption1)
        val rbOption2 = findViewById<RadioButton>(R.id.rbOption2)
        val rbOption3 = findViewById<RadioButton>(R.id.rbOption3)
        val rbOption4 = findViewById<RadioButton>(R.id.rbOption4)
        val btnNext = findViewById<Button>(R.id.btnNext)

        fun loadQuestion() {
            val q = questions[currentIndex]
            tvQuestion.text = q.text
            rbOption1.text = q.options[0]
            rbOption2.text = q.options[1]
            rbOption3.text = q.options[2]
            rbOption4.text = q.options[3]
            radioGroup.clearCheck()
            tvScore.text = "Score: $score / ${questions.size}"
            btnNext.text = if (currentIndex == questions.size - 1) "FINISH" else "NEXT"
        }

        loadQuestion()

        btnNext.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedIndex = when (selectedId) {
                R.id.rbOption1 -> 0
                R.id.rbOption2 -> 1
                R.id.rbOption3 -> 2
                R.id.rbOption4 -> 3
                else -> -1
            }

            if (selectedIndex == questions[currentIndex].correctIndex) {
                score++
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wrong! Answer: ${questions[currentIndex].options[questions[currentIndex].correctIndex]}", Toast.LENGTH_SHORT).show()
            }

            if (currentIndex < questions.size - 1) {
                currentIndex++
                loadQuestion()
            } else {
                tvQuestion.text = "Quiz Complete!\nYour Score: $score / ${questions.size}"
                tvScore.text = "Final Score: $score / ${questions.size}"
                radioGroup.removeAllViews()
                btnNext.isEnabled = false
            }
        }
    }
}

data class Question(val text: String, val options: List<String>, val correctIndex: Int)
