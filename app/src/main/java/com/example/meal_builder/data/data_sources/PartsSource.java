package com.example.meal_builder.data.data_sources;

import com.example.meal_builder.data.model.ChoosableMealPart;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PartsSource {
    private static String[] parts = new String[] {"Сыр", "Колбаса", "Салат", "Хлеб", "Творог", "Мюсли", "Банан", "Чай", "Квас", "Помидор"};
    private static String[] images = new String[] {"breakfast1", "fried_eggs", "salad", "sandwitch", "tomatoes"};
    public static ArrayList<ChoosableMealPart> choosableParts = new ArrayList<ChoosableMealPart>(){
        {
            for (int i = 0; i < parts.length; i++) {
                add(new ChoosableMealPart(i, random(0, 674), random(0, 234), random(0, 319), random(0, 199), parts[i], images[random(0, images.length - 1)]));
            }
        }
    };

    static private  int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
