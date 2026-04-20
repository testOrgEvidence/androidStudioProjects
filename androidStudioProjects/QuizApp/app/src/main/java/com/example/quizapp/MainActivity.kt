package com.example.quizapp

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val allQuestions = listOf(
        Question("What is the capital of France?", listOf("Berlin", "Paris", "Madrid", "Rome"), 1),
        Question("Which planet is known as the Red Planet?", listOf("Venus", "Jupiter", "Mars", "Saturn"), 2),
        Question("What is 7 x 8?", listOf("54", "56", "58", "64"), 1),
        Question("Who wrote Romeo and Juliet?", listOf("Dickens", "Shakespeare", "Austen", "Twain"), 1),
        Question("What is the largest ocean?", listOf("Atlantic", "Indian", "Arctic", "Pacific"), 3),
        Question("What is the chemical symbol for water?", listOf("O2", "H2O", "CO2", "NaCl"), 1),
        Question("Which country has the most population?", listOf("USA", "India", "China", "Brazil"), 1)
    )

    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var score = 0
    private var timer: CountDownTimer? = null
    private val timePerQuestion = 15000L

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

        val tvTimer = findViewById<TextView>(R.id.tvTimer)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.max = questions.size

        fun startTimer() {
            timer?.cancel()
            timer = object : CountDownTimer(timePerQuestion, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tvTimer.text = "⏱ ${millisUntilFinished / 1000}s"
                }
                override fun onFinish() {
                    tvTimer.text = "⏱ Time's up!"
                    Toast.makeText(this@MainActivity, "Time's up!", Toast.LENGTH_SHORT).show()
                    if (currentIndex < questions.size - 1) {
                        currentIndex++
                        loadQuestion()
                        startTimer()
                    } else {
                        showResultDialog()
                    }
                }
            }.start()
        }

        resetQuiz()
        progressBar.progress = 1
        loadQuestion()
        startTimer()

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
                progressBar.progress = currentIndex + 1
                loadQuestion()
                startTimer()
            } else {
                timer?.cancel()
                showResultDialog()
            }
        }
    }

    private fun resetQuiz() {
        questions = allQuestions.shuffled().take(5)
        currentIndex = 0
        score = 0
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }

    private fun showResultDialog() {
        val percentage = (score * 100) / questions.size
        val message = when {
            score == questions.size -> "🏆 Perfect! You got all correct!"
            percentage >= 80 -> "🌟 Excellent work!"
            percentage >= 50 -> "👍 Good job! You passed!"
            else -> "📚 Keep practicing!"
        }

        AlertDialog.Builder(this)
            .setTitle("Quiz Complete")
            .setMessage("$message\nScore: $score / ${questions.size}")
            .setPositiveButton("Play Again") { _, _ -> recreate() }
            .setNegativeButton("Exit") { _, _ -> finish() }
            .setCancelable(false)
            .show()
    }
}

data class Question(val text: String, val options: List<String>, val correctIndex: Int)
