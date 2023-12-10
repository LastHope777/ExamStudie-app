package com.example.myapplication

class Task(val questions: List<Question>, var userAnswer1: String = "", var userAnswer2: String = "") {
    var currentQuestionIndex = 0
        private set

    fun getCurrentQuestion(): Question {
        return questions[currentQuestionIndex]
    }
}
