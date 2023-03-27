package com.example.meal_builder;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MealPartsViewModel extends ViewModel {

    static ArrayList<ChoosableMealPart> choosableParts = new ArrayList<ChoosableMealPart>(){
        {
            add(new ChoosableMealPart(0, 123, 421, 333, 44, "Сыр", "salad"));
            add(new ChoosableMealPart(1, 123, 421, 333, 44, "Колбаса", "tomatoes"));

            for (int i = 2; i < 22; i++) {
                add(new ChoosableMealPart(i, 123, 421, 333, 44, "Сыр", "salad"));
            }
        }
    };

    public MutableLiveData<List<ChoosableMealPart>> possibleMealParts = new MutableLiveData<>(choosableParts);
    public MutableLiveData<List<ChoosableMealPart>> chosenMealParts = new MutableLiveData<>(new ArrayList<>());

    public ChoosableMealPart getMealPartById(int id) {
        return possibleMealParts.getValue().stream().filter(mealPart -> mealPart.id == id).findFirst().orElse(null);
    }

    public void addChosenPartById(int id) {
        chosenMealParts.getValue().add(getMealPartById(id));
    }

    public void clearChosenParts() {
        chosenMealParts.getValue().clear();
    }
}
