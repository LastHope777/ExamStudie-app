package com.example.myapplication

// QuestionFragment.kt
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentQuestionBinding
import com.squareup.picasso.Picasso

class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding
    private lateinit var task: Task
    private val questionViews = mutableListOf<View>()

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

        task.questions.forEachIndexed { index, question ->
            val questionView: View = binding.questionsContainer.getChildAt(index * 2)
            val userAnswerEditText: EditText =
                binding.questionsContainer.getChildAt(index * 2 + 1) as EditText

            setupQuestionViews(questionView, userAnswerEditText, question, index + 1)
        }
    }

    private fun setupQuestionViews(
        questionView: View,
        userAnswerEditText: EditText,
        question: Question,
        questionIndex: Int
    ) {
        if (question.isImageQuestion() && questionView is ImageView) {
            val imageName = "img${questionIndex}n1"
            val resourceId = resources.getIdentifier(imageName, "drawable", requireContext().packageName)

            if (resourceId != 0) {
                Picasso.get().load(resourceId).into(questionView)
            } else {
// Если изображение не найдено, используйте заглушку
                Picasso.get().load(R.drawable.placeholder_image).into(questionView)
            }
        }

// Устанавливаем текст вопроса в соответствующий EditText
        userAnswerEditText.hint = question.text

        userAnswerEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
// Опционально
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
// Опционально
            }

            override fun afterTextChanged(s: Editable?) {
// Опционально
            }
        })
    }
}