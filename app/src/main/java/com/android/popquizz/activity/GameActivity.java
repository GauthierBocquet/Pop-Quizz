package com.android.popquizz.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.popquizz.R;
import com.android.popquizz.bean.Question;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    List<Button> responseButtons = new ArrayList<>();
    TextView questionText;
    TextView timerText;
    CountDownTimer timer;
    CountDownTimer timerBetweenQuestions;
    int currentQuestionIndex = -1;
    List<Question> questions;
    int score= 0;
    RelativeLayout loadingPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        questionText = findViewById(R.id.questionText);
        timerText = findViewById(R.id.timerText);
        loadingPanel = findViewById(R.id.loadingPanel);

        this.questions = (List<Question>) this.getIntent().getSerializableExtra("questions");

        initButtons();

        startGame(this.questions);
    }
    private void initButtons(){
        responseButtons.add(findViewById(R.id.responseButton1));
        responseButtons.add(findViewById(R.id.responseButton2));
        responseButtons.add(findViewById(R.id.responseButton3));
        responseButtons.add(findViewById(R.id.responseButton4));

        responseButtons.forEach(button -> {
            button.setOnClickListener(view -> {
                checkAnswer(button);
            });
        });
    }

    private void refreshColorButton(){
        responseButtons.forEach( button ->
                button.setBackgroundColor(getResources().getColor(R.color.mainColor)));
    }

    private void checkAnswer(Button clickedButton){
        if(timer !=null){
            timer.cancel();
        }
        responseButtons.forEach( button -> {
            if(button.getText().toString().equals(questions.get(currentQuestionIndex).getCorrect_answer())){
                button.setBackgroundColor(Color.GREEN);
                if(button.equals(clickedButton)){
                    score ++;
                }
            }else if (button.equals(clickedButton)){
                button.setBackgroundColor(Color.RED);
            }
        });
        timerBetweenQuestions = new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                askQuestion();
            }
        }.start();
    }

    private void startGame(List<Question> questions) {
        this.questions = questions;
        responseButtons.forEach(button -> button.setVisibility(View.VISIBLE));
        loadingPanel.setVisibility(View.GONE);
        askQuestion();
    }

    private void askQuestion() {
        refreshColorButton();
        currentQuestionIndex++;
        if (currentQuestionIndex < 10) {

            List<String> answers = questions.get(currentQuestionIndex).getIncorrect_answers();
            answers.add(questions.get(currentQuestionIndex).getCorrect_answer());

            questionText.setText(questions.get(currentQuestionIndex).getQuestion());
            Collections.shuffle(responseButtons);

            int index = 0;
            for (Button responseButton : responseButtons) {
                responseButton.setText(answers.get(index));
                index++;
            }
            timer = new CountDownTimer(15000, 1000) {

                public void onTick(long millisUntilFinished) {
                    timerText.setText(new SimpleDateFormat("ss").format(new Date(millisUntilFinished)));
                }

                public void onFinish() {
                    checkAnswer(new Button(getApplicationContext()));
                }

            }.start();
        } else {
            goToScorePage();
        }
    }

    private void goToScorePage(){
        Intent myIntent = new Intent(this.getApplicationContext(), ScoreActivity.class);
        myIntent.putExtra("score", score);
        startActivity(myIntent);
    }
}
