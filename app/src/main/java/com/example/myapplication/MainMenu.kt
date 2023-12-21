package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val button_var1: Button = findViewById(R.id.button2)
        button_var1.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
    }
}