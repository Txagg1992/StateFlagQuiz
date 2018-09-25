package com.curiousca.stateflagquiz.Activities;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.curiousca.stateflagquiz.DataClasses.Question;
import com.curiousca.stateflagquiz.DataClasses.QuizDbHelper;
import com.curiousca.stateflagquiz.R;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ImageView imageViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageViewQuestion = findViewById(R.id.image_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountDown = findViewById(R.id.text_view_countdown);
        radioGroup = findViewById(R.id.radio_group);
        radioButton1 = findViewById(R.id.radio_button1);
        radioButton2 = findViewById(R.id.radio_button2);
        radioButton3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        textColorDefaultRb = radioButton1.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQueations();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();
    }

    private void showNextQuestion(){
        radioButton1.setTextColor(textColorDefaultRb);
        radioButton2.setTextColor(textColorDefaultRb);
        radioButton3.setTextColor(textColorDefaultRb);
        radioGroup.clearCheck();

        if (questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            //imageViewQuestion.setImageDrawable(currentQuestion.getImageByteArray());

        }
    }
}
