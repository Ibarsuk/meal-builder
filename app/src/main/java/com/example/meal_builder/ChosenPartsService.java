package com.example.meal_builder;

import java.util.ArrayList;

public class ChosenPartsService {
    private ArrayList<ChoosableMealPart> chosenParts = new ArrayList<ChoosableMealPart>();

    public ArrayList<ChoosableMealPart> getParts() {
        return chosenParts;
    }

    public void addPart(ChoosableMealPart part) {
        chosenParts.add(part);
    }
}
