package com.example.myapplication

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ImageView
import android.view.View



class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultTable: TableLayout = findViewById(R.id.resultTable)
        val backButton: Button = findViewById(R.id.backButton)

        val userAnswers = intent.getStringArrayListExtra("user_answers")
        if (userAnswers != null) {
            val correctAnswers = listOf("8", "4312", "6", "0,375", "0,52", "-2", "2", "-33", "50", "27", "5", "1,5")
            val correctAnswersList = correctAnswers.map { it.replace(",", ".") }.map { it.toDouble() }

            var correctCount = 0
            for (i in userAnswers.indices) {
                if (userAnswers[i] == correctAnswers[i]) {
                    correctCount++
                }
            }

            var PrimaryPoints = when (correctCount) {
                1 -> 6
                2 -> 11
                3 -> 17
                4 -> 22
                5 -> 27
                6 -> 34
                7 -> 40
                8 -> 46
                9 -> 52
                10 -> 58
                11 -> 64
                12 -> 66
                else -> 0
            }

            val incorrectAnswers = mutableListOf<String>()

            for (i in 1..12) {
                val userAnswer = userAnswers[i - 1]
                val correctAnswer = correctAnswers[i - 1].replace(",", ".").toDouble()

                if (userAnswer.toDoubleOrNull() != correctAnswer) {
                    incorrectAnswers.add(i.toString())
                }
            }

            val resultTextView: TextView = findViewById(R.id.resultTextView)
            val resultString = "Результат: Вы набрали $PrimaryPoints баллов из 66 (ЕГЭ) за первую часть!"
            resultTextView.text = resultString

            displayResults(resultTable, userAnswers, correctAnswers)
            displayIncorrectImages(incorrectAnswers.map { it.toString() })
        }

        backButton.setOnClickListener {
            onBackPressed()
        }
    }
    private fun displayIncorrectImages(incorrectAnswers: List<String>) {
        val imagesLayout: LinearLayout = findViewById(R.id.imagesLayout)
        val resources = resources

        for (incorrectAnswer in incorrectAnswers) {
            val imageView = ImageView(this)
            val resourceId = resources.getIdentifier("img${incorrectAnswer}n1", "drawable", packageName)

            if (resourceId != 0) {
                imageView.setImageResource(resourceId)
                imagesLayout.addView(imageView)
            }
        }
    }


    private fun displayResults(
        resultTable: TableLayout,
        userAnswers: ArrayList<String>?,
        correctAnswers: List<String>?
    ) {
        if (userAnswers != null && correctAnswers != null) {
            for ((index, userAnswer) in userAnswers.withIndex()) {
                val row = resultTable.getChildAt(index + 1) as? TableRow

                if (row != null) {
                    val userAnswerTextView = row.getChildAt(1) as? TextView
                    val correctAnswerTextView = row.getChildAt(2) as? TextView

                    userAnswerTextView?.text = userAnswer
                    correctAnswerTextView?.text = correctAnswers[index]
                }
            }
        }
    }
}