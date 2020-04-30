package com.android.popquizz.activity.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreboardItemFragment {

    private int score;

    private String name;

    ScoreboardItemFragment(int score, String name) {
        super();
        this.score = score;
        this.name = name;
    }

    public LinearLayout getLayout(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);

        ViewGroup.LayoutParams paramsText = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        TextView scoreText = new TextView(context);
        scoreText.setText(Integer.toString(score));
        scoreText.setTextSize(30);
        scoreText.setLayoutParams(paramsText);
        scoreText.setTextColor(Color.WHITE);
        scoreText.setPadding(10, 0, 10, 0);

        TextView nameText = new TextView(context);
        nameText.setText(this.name);
        nameText.setTextSize(30);
        nameText.setLayoutParams(paramsText);

        linearLayout.addView(scoreText);
        linearLayout.addView(nameText);

        return linearLayout;
    }
}
