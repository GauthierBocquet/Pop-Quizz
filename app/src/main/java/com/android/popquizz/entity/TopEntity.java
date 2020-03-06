package com.android.popquizz.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "top")
public class TopEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String name;
    private Integer score;

    public TopEntity(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        score = score;
    }
}
