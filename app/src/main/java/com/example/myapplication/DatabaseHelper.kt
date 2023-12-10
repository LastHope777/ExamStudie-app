package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.random.Random

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "QuizDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Questions"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_QUESTION_TEXT = "question_text"
        private const val COLUMN_CORRECT_ANSWER = "correct_answer"
        private const val COLUMN_TASK_NUMBER = "task_number"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_QUESTION_TEXT TEXT,
                $COLUMN_CORRECT_ANSWER TEXT,
                $COLUMN_TASK_NUMBER INTEGER
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addQuestion(questionText: String, correctAnswer: String, taskNumber: Int) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_QUESTION_TEXT, questionText)
            put(COLUMN_CORRECT_ANSWER, correctAnswer)
            put(COLUMN_TASK_NUMBER, taskNumber)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getQuestionsForTask(taskNumber: Int): List<Question> {
        val questions = mutableListOf<Question>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_TASK_NUMBER = $taskNumber"
        val cursor: Cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val questionText = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION_TEXT))
            val correctAnswer = cursor.getString(cursor.getColumnIndex(COLUMN_CORRECT_ANSWER))
            questions.add(Question(questionText, correctAnswer))
        }

        cursor.close()
        return questions
    }
}
