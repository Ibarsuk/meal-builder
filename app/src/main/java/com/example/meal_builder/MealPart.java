package com.example.meal_builder;

public class MealPart extends ChoosableMealPart{
    static int gramsDefaultCounting = 100;
    int grams = 100;

    MealPart(int calories, int fats, int protein, int carbohydrates, String name, String image, int grams) {
        super(calories, fats, protein, carbohydrates, name, image);
        this.grams = grams;
    }

    MealPart(ChoosableMealPart part, int grams) {
        super(part.calories, part.fats, part.protein, part.carbohydrates, part.name, part.image);
        this.grams = grams;
    }

    MealPart(ChoosableMealPart part) {
        super(part.calories, part.fats, part.protein, part.carbohydrates, part.name, part.image);
    }

    int getTotalCalories() {
        return Math.round((float)(this.calories * this.grams) / gramsDefaultCounting);
    }

    int getTotalFats() {
        return Math.round((float)(this.fats * this.grams) / gramsDefaultCounting);
    }

    int getTotalProtein() {
        return Math.round((float)(this.protein * this.grams) / gramsDefaultCounting);
    }

    int getTotalCarbohydrates() {
        return Math.round((float)(this.carbohydrates * this.grams) / gramsDefaultCounting);
    }
}
