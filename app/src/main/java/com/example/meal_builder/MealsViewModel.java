package com.example.meal_builder;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meal_builder.data.MealsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealsViewModel extends ViewModel {
    public MutableLiveData<List<UserMeal>> userMeals = new MutableLiveData<>(null);
    public MutableLiveData<UserMeal> editingMeal = new MutableLiveData<>(null);


    public List<UserMeal> getUserMeals() {
        if (userMeals.getValue() == null) {
            userMeals.setValue(MealsRepository.getMeals());
        }
        return userMeals.getValue();
    }

    public void addNewMeal() {
        UserMeal newMeal = MealsRepository.addMeal(new UserMeal());
        userMeals.getValue().add(newMeal);
        userMeals.setValue(userMeals.getValue());
    }

    public UserMeal getMealById(int id) {
        return userMeals.getValue().stream().filter(userMeal -> userMeal.id == id).findFirst().orElse(null);
    }

    public void setEditingMealById(int id) {
        editingMeal.setValue(new UserMeal(getMealById(id)));
    }

    public void saveEditingMeal() {
        UserMeal editing = editingMeal.getValue();
        userMeals.getValue().replaceAll(userMeal -> userMeal.id == editing.id ? editing : userMeal);
    }

    public void changeEditingMeal(UserMeal meal) {
        editingMeal.setValue(meal);
    }

    public void addPartsToEditingMeal(List<ChoosableMealPart> parts) {
        UserMeal meal = editingMeal.getValue();
        for (ChoosableMealPart part : parts) {
            meal.parts.add(new MealPart(part));
        }
    }
}
