package com.android.popquizz.bean;

import java.util.List;

public class Results {

    private List<Question> results;

    public Results(List<Question> results) {
        this.results = results;
    }

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }
}
