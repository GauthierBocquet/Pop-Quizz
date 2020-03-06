package com.android.popquizz.activity.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.popquizz.AppDatabase;
import com.android.popquizz.R;

public class ScoreboardFragment extends Fragment {
    private AppDatabase db;

    private LinearLayout topLinearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        topLinearLayout = (LinearLayout) inflater.inflate(R.layout.scoreboard_fragment, container, false);

        db = AppDatabase.getAppDatabase(this.getActivity().getApplicationContext());
        fillScoreBoard();
        return topLinearLayout;
    }

    private void fillScoreBoard() {
        db.topDao().getAllTops().forEach(entity -> System.out.println("Top :" + entity.getName() + "|" + entity.getScore()));

        db.topDao().getTops(3).forEach(topEntity -> {
                    TextView topText = new TextView(this.getActivity().getApplicationContext());

                    topText.setText(topEntity.getName() + "|" + topEntity.getScore());
            topText.setGravity(Gravity.CENTER);
                    topLinearLayout.addView(topText);
                }
        );
    }
}
