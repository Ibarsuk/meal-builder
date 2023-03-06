package com.example.meal_builder;

public class ChoosableMealPart {
    int fats;
    int protein;
    int carbohydrates;
    int calories;
    String name;
    String image;

    ChoosableMealPart(int calories, int fats, int protein, int carbohydrates, String name, String image) {
        this.calories = calories;
        this.fats = fats;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.name = name;
        this.image = image;
    }
}
