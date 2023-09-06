package com.example.meal_builder.ui.view;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meal_builder.ui.viewmodels.MealsViewModel;
import com.example.meal_builder.R;
import com.example.meal_builder.ui.adapters.UserMealsAdapter;
import com.example.meal_builder.ui.viewmodels.LoginViewModel;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;

public class MealsFragment extends Fragment {

    MealsViewModel mealsViewModel;

    private final ActivityResultLauncher<String> requestSavePermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.CreateDocument("txt/*"), uri -> {
                if (uri != null) {
                    try {
                        ParcelFileDescriptor txt = getActivity().getContentResolver().openFileDescriptor(uri, "w");
                        FileOutputStream fileOutputStream = new FileOutputStream(txt.getFileDescriptor());
                        fileOutputStream.write(new Gson().toJson(mealsViewModel.getUserMeals().getValue()).getBytes());
                        fileOutputStream.close();
                        txt.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
    public MealsFragment() {
        super(R.layout.fragment_meals);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mealsViewModel = new ViewModelProvider(getActivity()).get(MealsViewModel.class);
        LoginViewModel loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        RecyclerView mealsList = getView().findViewById(R.id.user_meals_container);
        UserMealsAdapter adapter = new UserMealsAdapter(getContext(), mealsViewModel, this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mealsList.setLayoutManager(manager);
        mealsList.setAdapter(adapter);

        Button addButton = getView().findViewById(R.id.add_meal_btn);
        addButton.setOnClickListener(view1 -> {
            mealsViewModel.addNewMeal();
        });

        Button logoutBtn = getView().findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(view1 -> {
            loginViewModel.logout();
            mealsViewModel.clear();
            Navigation.findNavController(getView()).navigate(
                    R.id.action_meals_to_login,
                    null,
                    new NavOptions.Builder().setPopUpTo(R.id.mealsFragment, true).build()
            );
        });

        Button exportButton = getView().findViewById(R.id.export_btn);
        exportButton.setOnClickListener(view1 -> {
            requestSavePermissionLauncher.launch("meals.txt");
        });
    }
}