package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.model.Category
import com.example.quizapp.model.Difficulty

class CategorySelectionActivity : AppCompatActivity() {

    private lateinit var selectedDifficulty: Difficulty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_selection)

        selectedDifficulty = Difficulty.valueOf(
            intent.getStringExtra("difficulty") ?: Difficulty.MEDIUM.name
        )

        findViewById<Button>(R.id.btnCategoryScience).setOnClickListener {
            startQuizWithCategory(Category.SCIENCE)
        }

        findViewById<Button>(R.id.btnCategoryGeography).setOnClickListener {
            startQuizWithCategory(Category.GEOGRAPHY)
        }

        findViewById<Button>(R.id.btnCategoryHistory).setOnClickListener {
            startQuizWithCategory(Category.HISTORY)
        }

        findViewById<Button>(R.id.btnCategoryMath).setOnClickListener {
            startQuizWithCategory(Category.MATH)
        }

        findViewById<Button>(R.id.btnCategoryLiterature).setOnClickListener {
            startQuizWithCategory(Category.LITERATURE)
        }

        findViewById<Button>(R.id.btnCategoryMixed).setOnClickListener {
            startQuizWithCategory(null)
        }

        findViewById<Button>(R.id.btnBackCategory).setOnClickListener { finish() }
    }

    private fun startQuizWithCategory(category: Category?) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("difficulty", selectedDifficulty.name)
            category?.let { putExtra("category", it.name) }
            putExtra("start_quiz", true)
        }
        startActivity(intent)
        finish()
    }
}
