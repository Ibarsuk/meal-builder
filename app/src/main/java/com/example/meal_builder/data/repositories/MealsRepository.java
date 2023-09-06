package com.example.meal_builder.data.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.meal_builder.data.DB;
import com.example.meal_builder.data.Mapper;
import com.example.meal_builder.data.data_sources.LoginDataSource;
import com.example.meal_builder.data.data_sources.UserMealDAO;
import com.example.meal_builder.data.entities.UserMealWithParts;
import com.example.meal_builder.data.model.UserMeal;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MealsRepository {
    private UserMealDAO userMealDAO;

    private MutableLiveData<List<UserMeal>> userMeals = new MutableLiveData<>(new ArrayList<>());
    private LoginRepository loginRepository;

    public MealsRepository(Application app) {
        DB db = DB.getDatabase(app);
        userMealDAO = db.userMealDAO();
        loginRepository = LoginRepository.getInstance(new LoginDataSource());
    }

    public MutableLiveData<List<UserMeal>> getMeals() {
        DB.databaseWriteExecutor.execute(() -> {
            Log.e("ID", String.valueOf(loginRepository.user.getUserId()));
            List<UserMealWithParts> meals = userMealDAO.getUserMealsWithParts(loginRepository.user.getUserId());
            userMeals.postValue(Mapper.mapUserMealToClient(meals));
        });

        return userMeals;
    }

    public void addEmptyMeal() {
        DB.databaseWriteExecutor.execute(() -> {
            UserMealWithParts meal = userMealDAO.insert(new com.example.meal_builder.data.entities.UserMeal(loginRepository.user.getUserId()));
            userMeals.getValue().add(Mapper.adaptUserMealToClient(meal));
            userMeals.postValue(userMeals.getValue());
        });
    }

    public void updateMeal(UserMeal mealToUpdate) {
        DB.databaseWriteExecutor.execute(() -> {
            UserMealWithParts updatedMeal = userMealDAO.update(Mapper.adaptUserMealToDB(mealToUpdate, loginRepository.user.getUserId()), mealToUpdate.parts);
            userMeals.getValue().replaceAll(
                    userMeal -> Objects.equals(userMeal.id, Integer.valueOf((int) updatedMeal.userMeal.mealId)) ?
                            Mapper.adaptUserMealToClient(updatedMeal) :
                            userMeal
            );

            userMeals.postValue(userMeals.getValue());
        });
    }
}
