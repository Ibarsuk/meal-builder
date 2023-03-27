package com.example.meal_builder.data;

import com.example.meal_builder.MealPart;
import com.example.meal_builder.UserMeal;

import java.util.ArrayList;
import java.util.Arrays;

public class MealsSource {
    public static ArrayList<UserMeal> meals = new ArrayList<UserMeal>(){
        {
            add(new UserMeal(0, "Мой завтрак", "breakfast1", Arrays.asList(new MealPart(0, 123, 421, 333, 44, "Сыр", "salad", 135))));
            add(new UserMeal(1, "Мой перекус", "salad", Arrays.asList(new MealPart(1, 123, 421, 333, 44, "Колбаса", "tomatoes", 231))));
        }
    };

    public static UserMeal add(UserMeal toAdd) {
        toAdd.id = meals.size();
        meals.add(toAdd);
        return toAdd;
    }
}
