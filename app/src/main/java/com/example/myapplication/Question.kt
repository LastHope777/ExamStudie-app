package com.example.myapplication

data class Question(val text: String, val imageUrl: String? = null) {
    fun isImageQuestion(): Boolean {
        return imageUrl != null
    }
}
