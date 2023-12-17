package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tasks: List<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Создание экземпляра DatabaseHelper
        //val dbHelper = DatabaseHelper(this)

        tasks = getTasks()

        viewPager = findViewById(R.id.viewPager)
        val pagerAdapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int = 1

            override fun getItem(position: Int): Fragment {
                return QuestionFragment.newInstance(tasks[position % tasks.size])
            }
        }

    viewPager.adapter = pagerAdapter
    }

    // Получите ваши задания из базы данных или другого источника данных
    private fun getTasks(): List<Task> {
        val poolQuestion1 = listOf(
            Question("1 + 1 = ?", "2"),
            Question("2 + 3 = ?", "5")
        )

        val poolQuestion2 = listOf(
            Question("3 + 2x = 5, решите для x", "1"),
            Question("1 + 2x = 3, решите для x", "1")
        )

        val task1 = Task(poolQuestion1, "", "")
        val task2 = Task(poolQuestion2, "", "")

        return listOf(task1, task2)
    }
}
