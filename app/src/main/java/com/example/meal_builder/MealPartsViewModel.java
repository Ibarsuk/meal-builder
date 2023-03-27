package com.example.meal_builder;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.meal_builder.data.PartsRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MealPartsViewModel extends ViewModel {

    public MutableLiveData<List<ChoosableMealPart>> possibleMealParts = new MutableLiveData<>(null);
    public MutableLiveData<List<ChoosableMealPart>> chosenMealParts = new MutableLiveData<>(new ArrayList<>());

    public List<ChoosableMealPart> getPossibleMealParts() {
        if (possibleMealParts.getValue() == null) {
            possibleMealParts.setValue(PartsRepository.get());
        }
        return possibleMealParts.getValue();
    }

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
