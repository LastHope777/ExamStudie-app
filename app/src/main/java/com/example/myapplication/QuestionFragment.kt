package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentQuestionBinding
import com.squareup.picasso.Picasso

class QuestionFragment : Fragment() {

    private lateinit var binding: FragmentQuestionBinding
    private lateinit var task: Task
    private lateinit var userAnswers: MutableList<String>

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
        userAnswers = MutableList(12) { "" }
        task.questions.forEachIndexed { index, question ->
            val questionView: View = binding.questionsContainer.getChildAt(index * 2)
            val userAnswerEditText: EditText =
                binding.questionsContainer.getChildAt(index * 2 + 1) as EditText

            setupQuestionViews(questionView, userAnswerEditText, question, index +1, 12)
        }


        val finishButton: Button = binding.finishButton
        finishButton.setOnClickListener {

            val answer1: EditText = view.findViewById(R.id.userAnswerEditText1)
            val answer2: EditText = view.findViewById(R.id.userAnswerEditText2)
            val answer3: EditText = view.findViewById(R.id.userAnswerEditText3)
            val answer4: EditText = view.findViewById(R.id.userAnswerEditText4)
            val answer5: EditText = view.findViewById(R.id.userAnswerEditText5)
            val answer6: EditText = view.findViewById(R.id.userAnswerEditText6)
            val answer7: EditText = view.findViewById(R.id.userAnswerEditText7)
            val answer8: EditText = view.findViewById(R.id.userAnswerEditText8)
            val answer9: EditText = view.findViewById(R.id.userAnswerEditText9)
            val answer10: EditText = view.findViewById(R.id.userAnswerEditText10)
            val answer11: EditText = view.findViewById(R.id.userAnswerEditText11)
            val answer12: EditText = view.findViewById(R.id.userAnswerEditText12)

            val userAnswers = arrayListOf(
                answer1.text.toString(),
                answer2.text.toString(),
                answer3.text.toString(),
                answer4.text.toString(),
                answer5.text.toString(),
                answer6.text.toString(),
                answer7.text.toString(),
                answer8.text.toString(),
                answer9.text.toString(),
                answer10.text.toString(),
                answer11.text.toString(),
                answer12.text.toString()
            )

            val intent = Intent(activity, ResultActivity::class.java)
            intent.putStringArrayListExtra("user_answers", ArrayList(userAnswers))
            activity?.startActivity(intent)
        }
        }




    private fun setupQuestionViews(
        questionView: View,
        userAnswerEditText: EditText,
        question: Question,
        questionIndex: Int,
        number: Int
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
                val userAnswer = s?.toString() ?: ""
                userAnswers[number - 1] = userAnswer
            }
        })

    }

}