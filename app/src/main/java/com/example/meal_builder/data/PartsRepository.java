package com.example.meal_builder.data;

import com.example.meal_builder.ChoosableMealPart;

import java.util.ArrayList;
import java.util.List;

public class PartsRepository {
    static public List<ChoosableMealPart> get() {
        return new ArrayList<>(PartsSource.choosableParts);
    }

    static public ChoosableMealPart add(ChoosableMealPart partToAdd) {
        partToAdd.id = PartsSource.choosableParts.size();
        PartsSource.choosableParts.add(partToAdd);
        return partToAdd;
    }
}
