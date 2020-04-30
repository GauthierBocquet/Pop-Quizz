package com.android.popquizz.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.popquizz.API;
import com.android.popquizz.APIMapping;
import com.android.popquizz.R;
import com.android.popquizz.bean.Question;
import com.android.popquizz.bean.Results;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jsoup.Jsoup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view) {
        Spinner themeChoice = (Spinner) findViewById(R.id.spinner);
        Spinner difficultyChoice = (Spinner) findViewById(R.id.spinner2);

        if (!themeChoice.getSelectedItem().toString().equals("-") && !difficultyChoice.getSelectedItem().toString().equals("-")) {
            String theme = themeChoice.getSelectedItem().toString();
            String difficulty = difficultyChoice.getSelectedItem().toString();
            System.out.println("choosen theme : " + theme);
            getQuestions(theme, difficulty);
        }
    }

    private void getQuestions(String theme, String difficulty) {

        List<Question> result = new ArrayList<>();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)

                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);

        Call<Results> questions = api.getQuestions("application/json", "api.php?amount=10&type=multiple&category=" + APIMapping.getIdByTheme(theme) + "&difficulty=" + APIMapping.translateDifficulty(difficulty));


        System.out.println(API.BASE_URL + "api.php?amount=10&type=multiple&category=" + APIMapping.getIdByTheme(theme) + "&difficulty=" + APIMapping.translateDifficulty(difficulty));


        questions.enqueue(new Callback<Results>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {

                result.addAll(response.body().getResults());

                result.forEach(question -> {
                    question.setCategory(Jsoup.parse(question.getCategory()).text());
                    question.setCorrect_answer(Jsoup.parse(question.getCorrect_answer()).text());
                    question.setDifficulty(Jsoup.parse(question.getDifficulty()).text());
                    question.setQuestion(Jsoup.parse(question.getQuestion()).text());

                    List<String> incorrectAnswers = new ArrayList<>();
                    for (int i = 0; i < question.getIncorrect_answers().size(); i++) {
                        incorrectAnswers.add(Jsoup.parse(question.getIncorrect_answers().get(i)).text());
                    }
                    question.setIncorrect_answers(incorrectAnswers);
                });


                Intent intent = new Intent(getBaseContext(), GameActivity.class);
                intent.putExtra("questions", (Serializable) result);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                System.out.println("error");
            }
        });
    }

    public void goToInfoPage(View view) {
        Intent intent = new Intent(getBaseContext(), InformationActivity.class);
        startActivity(intent);
    }
}