package com.android.popquizz;

import java.util.*;

public class APIMapping {

    static Map<String, Integer> themesMap = new HashMap<String, Integer>() {{
        put("General Knowledge",9);
        put("Books",10);
        put("Films",11);
        put("Music",12);
        put("TV",14);
        put("Video Games",15);
        put("Science and Nature",17);
        put("Computer",18);
        put("Maths",19);
        put("Sports",21);
        put("Geography",22);
        put("History",23);
        put("Politics",24);
        put("Arts", 25);
    }};

    public static String translateDifficulty(String difficulty) {
        if(difficulty.equals("Facile")) {
            return "easy";
        } else if(difficulty.equals("Moyen")) {
            return "medium";
        } else {
            return "hard";
        }
    }

    public static Integer getIdByTheme(String theme) {
        Integer val = themesMap.get(theme);
        return val;
    }
}