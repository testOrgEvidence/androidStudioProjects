package com.example.quizapp.model

data class Question(
    val text: String,
    val options: List<String>,
    val correctIndex: Int,
    val category: Category,
    val difficulty: Difficulty
)

enum class Category { SCIENCE, GEOGRAPHY, HISTORY, MATH, LITERATURE }

enum class Difficulty { EASY, MEDIUM, HARD }

object QuestionBank {

    private val questions = listOf(
        // EASY
        Question("What is the capital of France?", listOf("Berlin", "Paris", "Madrid", "Rome"), 1, Category.GEOGRAPHY, Difficulty.EASY),
        Question("What is 7 x 8?", listOf("54", "56", "58", "64"), 1, Category.MATH, Difficulty.EASY),
        Question("How many continents are there?", listOf("5", "6", "7", "8"), 2, Category.GEOGRAPHY, Difficulty.EASY),
        Question("What color is the sky on a clear day?", listOf("Green", "Blue", "Red", "Yellow"), 1, Category.SCIENCE, Difficulty.EASY),
        Question("Who wrote Romeo and Juliet?", listOf("Dickens", "Shakespeare", "Austen", "Twain"), 1, Category.LITERATURE, Difficulty.EASY),

        // MEDIUM
        Question("Which planet is known as the Red Planet?", listOf("Venus", "Jupiter", "Mars", "Saturn"), 2, Category.SCIENCE, Difficulty.MEDIUM),
        Question("What is the largest ocean?", listOf("Atlantic", "Indian", "Arctic", "Pacific"), 3, Category.GEOGRAPHY, Difficulty.MEDIUM),
        Question("What is the chemical symbol for water?", listOf("O2", "H2O", "CO2", "NaCl"), 1, Category.SCIENCE, Difficulty.MEDIUM),
        Question("Which country has the most population?", listOf("USA", "India", "China", "Brazil"), 1, Category.GEOGRAPHY, Difficulty.MEDIUM),
        Question("What year did the Titanic sink?", listOf("1905", "1912", "1920", "1898"), 1, Category.HISTORY, Difficulty.MEDIUM),
        Question("Which gas do plants absorb?", listOf("Oxygen", "Nitrogen", "CO2", "Hydrogen"), 2, Category.SCIENCE, Difficulty.MEDIUM),
        Question("What is the square root of 144?", listOf("10", "11", "12", "14"), 2, Category.MATH, Difficulty.MEDIUM),

        // HARD
        Question("What is the speed of light in km/s?", listOf("150,000", "300,000", "450,000", "600,000"), 1, Category.SCIENCE, Difficulty.HARD),
        Question("Who painted the Mona Lisa?", listOf("Michelangelo", "Da Vinci", "Raphael", "Donatello"), 1, Category.HISTORY, Difficulty.HARD),
        Question("What is the derivative of x²?", listOf("x", "2x", "x²", "2"), 1, Category.MATH, Difficulty.HARD),
        Question("Which element has atomic number 79?", listOf("Silver", "Gold", "Platinum", "Copper"), 1, Category.SCIENCE, Difficulty.HARD),
        Question("In which year did World War I begin?", listOf("1912", "1914", "1916", "1918"), 1, Category.HISTORY, Difficulty.HARD),
        Question("What is the capital of Mongolia?", listOf("Astana", "Ulaanbaatar", "Bishkek", "Tashkent"), 1, Category.GEOGRAPHY, Difficulty.HARD)
    )

    fun getQuestions(difficulty: Difficulty, count: Int = 5): List<Question> {
        return questions.filter { it.difficulty == difficulty }.shuffled().take(count)
    }

    fun getQuestionsByCategory(category: Category, count: Int = 5): List<Question> {
        return questions.filter { it.category == category }.shuffled().take(count)
    }

    fun getMixedQuestions(count: Int = 5): List<Question> {
        return questions.shuffled().take(count)
    }
}
