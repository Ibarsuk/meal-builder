package com.example.meal_builder.data.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.meal_builder.data.data_sources.PartsSource;
import com.example.meal_builder.data.model.ChoosableMealPart;
import com.example.meal_builder.data.model.UserMeal;

import java.util.ArrayList;
import java.util.List;

public class PartsRepository {
    private PartsSource partsSource;

    public PartsRepository() {
        partsSource = new PartsSource();
    }

     public MutableLiveData<List<ChoosableMealPart>> get() {
        MutableLiveData<List<ChoosableMealPart>> result = new MutableLiveData<>();

        result.setValue(partsSource.get());

        return result;
    }

    static public ChoosableMealPart add(ChoosableMealPart partToAdd) {
        partToAdd.id = PartsSource.choosableParts.size();
        PartsSource.choosableParts.add(partToAdd);
        return partToAdd;
    }
}
