package com.android.popquizz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.popquizz.AppDatabase;
import com.android.popquizz.R;
import com.android.popquizz.entity.TopEntity;

public class ScoreActivity extends AppCompatActivity {
    AppDatabase db;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        db = AppDatabase.getAppDatabase(this.getApplicationContext());
        Intent intent = getIntent();
        score = intent.getExtras().getInt("score");

        TextView scoreText = findViewById(R.id.scoreText);
        scoreText.setText(Integer.toString(score));
    }

    public void goHome(View view) {
        Intent myIntent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
    }

    public void insertScore(View view) {
        EditText nameEditText = findViewById(R.id.nameEditText);

        db.topDao().insertTop(new TopEntity(nameEditText.getText().toString(), score));

        Toast.makeText(this.getApplicationContext(), "Score saved !", Toast.LENGTH_LONG).show();
        this.recreate();
    }
}
