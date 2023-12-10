package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding
    private lateinit var task: Task

    companion object {
        fun newInstance(task: Task): QuestionFragment {
            val fragment = QuestionFragment()
            fragment.task = task
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Проверка на наличие двух вопросов
        if (task.questions.size >= 2) {
            // Выбор случайных вопросов
            val randomIndex1 = (0 until task.questions.size).random()
            val randomIndex2 = (0 until task.questions.size).random()

            binding.questionTextView1.text = task.questions[randomIndex1].text
            binding.userAnswerEditText1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Опционально
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Опционально
                }

                override fun afterTextChanged(s: Editable?) {
                    task.userAnswer1 = s.toString()
                }
            })

            binding.questionTextView2.text = task.questions[randomIndex2].text
            binding.userAnswerEditText2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Опционально
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Опционально
                }

                override fun afterTextChanged(s: Editable?) {
                    task.userAnswer2 = s.toString()
                }
            })
        }
    }
}
