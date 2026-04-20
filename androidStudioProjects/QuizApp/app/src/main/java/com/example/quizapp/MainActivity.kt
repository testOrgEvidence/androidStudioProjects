package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.model.Difficulty
import com.example.quizapp.model.Question
import com.example.quizapp.model.QuestionBank
import com.example.quizapp.util.ScoreManager

class MainActivity : AppCompatActivity() {

    private lateinit var scoreManager: ScoreManager
    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var score = 0
    private var streak = 0
    private var bestStreak = 0
    private var timer: CountDownTimer? = null
    private var selectedDifficulty = Difficulty.MEDIUM

    private lateinit var menuLayout: LinearLayout
    private lateinit var quizLayout: LinearLayout
    private lateinit var tvQuestion: TextView
    private lateinit var tvScore: TextView
    private lateinit var tvTimer: TextView
    private lateinit var tvQuestionNumber: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var progressBar: ProgressBar
    private lateinit var btnNext: Button

    private val timePerQuestion: Long
        get() = when (selectedDifficulty) {
            Difficulty.EASY -> 20000L
            Difficulty.MEDIUM -> 15000L
            Difficulty.HARD -> 10000L
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreManager = ScoreManager(this)
        initViews()
        showMenu()
    }

    private fun initViews() {
        menuLayout = findViewById(R.id.menuLayout)
        quizLayout = findViewById(R.id.quizLayout)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvScore = findViewById(R.id.tvScore)
        tvTimer = findViewById(R.id.tvTimer)
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        radioGroup = findViewById(R.id.radioGroup)
        progressBar = findViewById(R.id.progressBar)
        btnNext = findViewById(R.id.btnNext)

        findViewById<Button>(R.id.btnEasy).setOnClickListener { startQuiz(Difficulty.EASY) }
        findViewById<Button>(R.id.btnMedium).setOnClickListener { startQuiz(Difficulty.MEDIUM) }
        findViewById<Button>(R.id.btnHard).setOnClickListener { startQuiz(Difficulty.HARD) }
        findViewById<Button>(R.id.btnStats).setOnClickListener { showStats() }
        findViewById<Button>(R.id.btnLeaderboard).setOnClickListener {
            startActivity(Intent(this, LeaderboardActivity::class.java))
        }

        btnNext.setOnClickListener { handleAnswer() }
    }

    private fun showMenu() {
        menuLayout.visibility = View.VISIBLE
        quizLayout.visibility = View.GONE
        timer?.cancel()

        val tvHighScores = findViewById<TextView>(R.id.tvHighScores)
        tvHighScores.text = "High Scores:\n" +
                "Easy: ${scoreManager.getHighScore(Difficulty.EASY)}%\n" +
                "Medium: ${scoreManager.getHighScore(Difficulty.MEDIUM)}%\n" +
                "Hard: ${scoreManager.getHighScore(Difficulty.HARD)}%"
    }

    private fun startQuiz(difficulty: Difficulty) {
        selectedDifficulty = difficulty
        questions = QuestionBank.getQuestions(difficulty)
        currentIndex = 0
        score = 0
        streak = 0
        bestStreak = 0

        menuLayout.visibility = View.GONE
        quizLayout.visibility = View.VISIBLE
        progressBar.max = questions.size
        progressBar.progress = 1

        loadQuestion()
        startTimer()
    }

    private fun loadQuestion() {
        val q = questions[currentIndex]
        tvQuestion.text = q.text
        tvQuestionNumber.text = "Q${currentIndex + 1}/${questions.size} • ${q.category.name}"
        findViewById<RadioButton>(R.id.rbOption1).text = q.options[0]
        findViewById<RadioButton>(R.id.rbOption2).text = q.options[1]
        findViewById<RadioButton>(R.id.rbOption3).text = q.options[2]
        findViewById<RadioButton>(R.id.rbOption4).text = q.options[3]
        radioGroup.clearCheck()
        tvScore.text = "Score: $score"
        btnNext.text = if (currentIndex == questions.size - 1) "FINISH" else "NEXT"
        resetOptionColors()
    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(timePerQuestion, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                tvTimer.text = "⏱ ${seconds}s"
                tvTimer.setTextColor(if (seconds <= 5) Color.RED else Color.parseColor("#D32F2F"))
            }

            override fun onFinish() {
                tvTimer.text = "⏱ 0s"
                streak = 0
                Toast.makeText(this@MainActivity, "⏰ Time's up!", Toast.LENGTH_SHORT).show()
                moveToNext()
            }
        }.start()
    }

    private fun handleAnswer() {
        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        timer?.cancel()

        val selectedIndex = when (selectedId) {
            R.id.rbOption1 -> 0
            R.id.rbOption2 -> 1
            R.id.rbOption3 -> 2
            R.id.rbOption4 -> 3
            else -> -1
        }

        val correct = selectedIndex == questions[currentIndex].correctIndex

        if (correct) {
            score++
            streak++
            if (streak > bestStreak) bestStreak = streak
            val streakMsg = if (streak >= 3) " 🔥 $streak streak!" else ""
            Toast.makeText(this, "✅ Correct!$streakMsg", Toast.LENGTH_SHORT).show()
            highlightOption(selectedId, Color.parseColor("#4CAF50"))
        } else {
            streak = 0
            val correctAnswer = questions[currentIndex].options[questions[currentIndex].correctIndex]
            Toast.makeText(this, "❌ Wrong! Answer: $correctAnswer", Toast.LENGTH_SHORT).show()
            highlightOption(selectedId, Color.parseColor("#F44336"))
            highlightCorrectOption()
        }

        btnNext.postDelayed({ moveToNext() }, 1000)
    }

    private fun moveToNext() {
        if (currentIndex < questions.size - 1) {
            currentIndex++
            progressBar.progress = currentIndex + 1
            loadQuestion()
            startTimer()
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        timer?.cancel()
        scoreManager.saveScore(selectedDifficulty, score, questions.size)
        scoreManager.saveBestStreak(bestStreak)

        val percentage = (score * 100) / questions.size
        val message = when {
            score == questions.size -> "🏆 Perfect! You got all correct!"
            percentage >= 80 -> "🌟 Excellent work!"
            percentage >= 50 -> "👍 Good job! You passed!"
            else -> "📚 Keep practicing!"
        }

        val streakInfo = if (bestStreak >= 2) "\nBest Streak: $bestStreak 🔥" else ""
        val diffLabel = selectedDifficulty.name.lowercase().replaceFirstChar { it.uppercase() }

        AlertDialog.Builder(this)
            .setTitle("Quiz Complete - $diffLabel")
            .setMessage("$message\nScore: $score / ${questions.size} ($percentage%)$streakInfo")
            .setPositiveButton("Play Again") { _, _ -> startQuiz(selectedDifficulty) }
            .setNeutralButton("Menu") { _, _ -> showMenu() }
            .setNegativeButton("Exit") { _, _ -> finish() }
            .setCancelable(false)
            .show()
    }

    private fun showStats() {
        val stats = "Games Played: ${scoreManager.getTotalGamesPlayed()}\n" +
                "Total Correct Answers: ${scoreManager.getTotalCorrectAnswers()}\n" +
                "All-Time Best Streak: ${scoreManager.getBestStreak()} 🔥\n\n" +
                "High Scores:\n" +
                "Easy: ${scoreManager.getHighScore(Difficulty.EASY)}%\n" +
                "Medium: ${scoreManager.getHighScore(Difficulty.MEDIUM)}%\n" +
                "Hard: ${scoreManager.getHighScore(Difficulty.HARD)}%"

        AlertDialog.Builder(this)
            .setTitle("📊 Your Stats")
            .setMessage(stats)
            .setPositiveButton("OK", null)
            .setNegativeButton("Reset") { _, _ ->
                scoreManager.resetAllScores()
                Toast.makeText(this, "Scores reset!", Toast.LENGTH_SHORT).show()
                showMenu()
            }
            .show()
    }

    private fun highlightOption(selectedId: Int, color: Int) {
        findViewById<RadioButton>(selectedId).setTextColor(color)
    }

    private fun highlightCorrectOption() {
        val correctIdx = questions[currentIndex].correctIndex
        val correctId = when (correctIdx) {
            0 -> R.id.rbOption1
            1 -> R.id.rbOption2
            2 -> R.id.rbOption3
            else -> R.id.rbOption4
        }
        findViewById<RadioButton>(correctId).setTextColor(Color.parseColor("#4CAF50"))
    }

    private fun resetOptionColors() {
        listOf(R.id.rbOption1, R.id.rbOption2, R.id.rbOption3, R.id.rbOption4).forEach {
            findViewById<RadioButton>(it).setTextColor(Color.BLACK)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}
