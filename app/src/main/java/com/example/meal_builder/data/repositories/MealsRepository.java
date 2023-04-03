package com.example.meal_builder.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.meal_builder.data.data_sources.MealsSource;
import com.example.meal_builder.data.model.UserMeal;

import java.util.ArrayList;
import java.util.List;

public class MealsRepository {
    MealsSource mSource;

    public MealsRepository() {
        mSource = new MealsSource();
    }

    public MutableLiveData<List<UserMeal>> getMeals() {
        MutableLiveData<List<UserMeal>> result = new MutableLiveData<>();

        result.setValue(mSource.get());

        return result;
    }

    public UserMeal addMeal(UserMeal mealToAdd) {
        return mSource.add(mealToAdd);
    }
}
