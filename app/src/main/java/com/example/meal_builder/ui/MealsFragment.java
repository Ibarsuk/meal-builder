package com.example.meal_builder.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meal_builder.MealsViewModel;
import com.example.meal_builder.R;
import com.example.meal_builder.UserMealsAdapter;

public class MealsFragment extends Fragment {
    public MealsFragment() {
        super(R.layout.fragment_meals);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        MealsViewModel mealsViewModel = new ViewModelProvider(getActivity()).get(MealsViewModel.class);

        RecyclerView mealsList = getView().findViewById(R.id.user_meals_container);
        UserMealsAdapter adapter = new UserMealsAdapter(getContext(), mealsViewModel, this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mealsList.setLayoutManager(manager);
        mealsList.setAdapter(adapter);

        Button addButton = getView().findViewById(R.id.add_meal_btn);
        addButton.setOnClickListener(view1 -> {
            mealsViewModel.addNewMeal();
            Log.e("S", String.valueOf(mealsViewModel.getUserMeals().size()));
        });
    }
}