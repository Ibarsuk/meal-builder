package com.example.meal_builder.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "user_meals")
public class UserMeal {
    @PrimaryKey(autoGenerate = true)
    public long mealId;

    public long userId;

    public String name;

    public String image;
@Ignore
    public UserMeal(String name, String image, long userId) {
        this.name = name;
        this.image = image;
        this.userId = userId;
    }

    public UserMeal(long mealId, String name, String image, long userId) {
        this.mealId = mealId;
        this.userId = userId;
        this.name = name;
        this.image = image;
    }
@Ignore
    public UserMeal(long userId) {
        this.userId = userId;
        this.name = "Новая трапеза";
        this.image = "empty";
    }
}
