package com.android.popquizz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.popquizz.R;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_layout);
    }

    public void firstPage(View view) {
        Intent myIntent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(myIntent);
    }
}
