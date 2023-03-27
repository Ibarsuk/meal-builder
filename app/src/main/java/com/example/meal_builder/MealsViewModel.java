package com.example.meal_builder;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealsViewModel extends ViewModel {

    public static ArrayList<UserMeal> choosableParts = new ArrayList<UserMeal>(){
        {
            add(new UserMeal(0, "Мой завтрак", "breakfast1", Arrays.asList(new MealPart(0, 123, 421, 333, 44, "Сыр", "salad", 135))));
            add(new UserMeal(1, "Мой перекус", "salad", Arrays.asList(new MealPart(1, 123, 421, 333, 44, "Колбаса", "tomatoes", 231))));
        }
    };

    public MutableLiveData<List<UserMeal>> userMeals = new MutableLiveData<>(choosableParts);
    public MutableLiveData<UserMeal> editingMeal = new MutableLiveData<>(null);

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
