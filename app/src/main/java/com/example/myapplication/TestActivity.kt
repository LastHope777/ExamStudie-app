package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tasks = getTasks()

        viewPager = findViewById(R.id.viewPager)
        val pagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int = tasks.size

            override fun getItem(position: Int): Fragment {
                return QuestionFragment.newInstance(tasks[position])
            }
        }

        viewPager.adapter = pagerAdapter
    }

    private fun getTasks(): List<Task> {
        val taskImages = (1..12).map { "img${it}n1" } // Предположим, что у вас есть 12 изображений с такими именами

        return taskImages.map { imageUrl ->
            Task((1..12).map { Question("Your Answer $it", imageUrl) })
        }
    }
}