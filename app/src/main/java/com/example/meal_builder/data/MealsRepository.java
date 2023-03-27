package com.example.meal_builder.data;

import com.example.meal_builder.MealPart;
import com.example.meal_builder.UserMeal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealsRepository {
    public static List<UserMeal> getMeals() {
        return new ArrayList<>(MealsSource.meals);
    }

    public static UserMeal addMeal(UserMeal mealToAdd) {
        return MealsSource.add(mealToAdd);
    }
}
