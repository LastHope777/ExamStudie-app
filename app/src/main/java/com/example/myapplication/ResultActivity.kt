package com.example.myapplication

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultTable: TableLayout = findViewById(R.id.resultTable)
        val backButton: Button = findViewById(R.id.backButton)
        // Получаем данные из Intent
        val userAnswers = intent.getStringArrayListExtra("user_answers")

        // Предположим, что у вас есть список правильных ответов
        val correctAnswers =
            listOf("8", "6", "6", "0,375", "0,52", "-2", "2", "-33", "50", "27", "5", "1,5")

        // Отображаем результаты в виде таблицы
        displayResults(resultTable, userAnswers, correctAnswers)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun displayResults(
        resultTable: TableLayout,
        userAnswers: ArrayList<String>?,
        correctAnswers: List<String>?
    ) {
        if (userAnswers != null && correctAnswers != null) {
            for ((index, userAnswer) in userAnswers.withIndex()) {
                // Получаем ссылку на существующую строку
                val row = resultTable.getChildAt(index + 1) as? TableRow

                if (row != null) {
                    // Находим текстовые поля внутри строки
                    val userAnswerTextView = row.getChildAt(1) as? TextView
                    val correctAnswerTextView = row.getChildAt(2) as? TextView

                    // Устанавливаем новые значения
                    userAnswerTextView?.text = userAnswer
                    correctAnswerTextView?.text = correctAnswers[index]
                }
            }
        }
    }
}

