package com.example.meal_builder.data.repositories;

import com.example.meal_builder.data.data_sources.MealsSource;
import com.example.meal_builder.data.model.UserMeal;

import java.util.ArrayList;
import java.util.List;

public class MealsRepository {
    public static List<UserMeal> getMeals() {
        return new ArrayList<>(MealsSource.meals);
    }

    public static UserMeal addMeal(UserMeal mealToAdd) {
        return MealsSource.add(mealToAdd);
    }
}
