package com.example.meal_builder.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meal_builder.ChosenPartsService;
import com.example.meal_builder.MealPartsViewModel;
import com.example.meal_builder.MealsViewModel;
import com.example.meal_builder.R;
import com.example.meal_builder.ChoosableMealPart;

import java.util.ArrayList;
import java.util.List;

public class ChoosePartsFragment extends Fragment implements DefaultLifecycleObserver {

    private final String TAG = this.getClass().getSimpleName();
    ArrayList<ChoosableMealPart> chosenParts = new ArrayList<ChoosableMealPart>();
    ChosenPartsService partsService = new ChosenPartsService();
    MealPartsViewModel mealPartsViewModel;
    MealsViewModel mealsViewModel;

    public ChoosePartsFragment() {
        super(R.layout.fragment_choose_parts);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mealPartsViewModel = new ViewModelProvider(getActivity()).get(MealPartsViewModel.class);
        mealsViewModel = new ViewModelProvider(getActivity()).get(MealsViewModel.class);

        RecyclerView partsList = getView().findViewById(R.id.part_variants_container);
        MealPartRecyclerAdapter adapter = new MealPartRecyclerAdapter(getContext(), mealPartsViewModel, this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        partsList.setLayoutManager(manager);
        partsList.setAdapter(adapter);

        Button cancelBtn = (Button) getView().findViewById(R.id.chooseCancelBtn);
        cancelBtn.setOnClickListener((cancelBtn1) -> {
            Navigation.findNavController(view).popBackStack();
        });

        Button saveBtn = (Button) getView().findViewById(R.id.chooseSaveBtn);
        saveBtn.setOnClickListener((saveBtn1) -> {
            mealsViewModel.addPartsToEditingMeal(mealPartsViewModel.chosenMealParts.getValue());
            Navigation.findNavController(view).popBackStack();
        });
    }

    public void addChosenPart(ChoosableMealPart part) {
        this.chosenParts.add(part);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach");
        Toast.makeText(context, "onAttach", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        Log.i(TAG, "onCreate");
        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        if (isVisible() && !partsService.getParts().isEmpty()) {
            Intent intent = new Intent(getContext(), OverlayService.class);
            StringBuilder notSavedParts = new StringBuilder();
            for (ChoosableMealPart part : partsService.getParts()) {
                notSavedParts.append(part.name).append(" ");
            }
            intent.putExtra("NotSaved", notSavedParts.toString());
            getContext().startService(intent);
        }
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        if (isVisible() && !partsService.getParts().isEmpty()) {
            getContext().stopService(new Intent(getContext(), OverlayService.class));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        Toast.makeText(getContext(), "onCreateView", Toast.LENGTH_SHORT).show();

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mealPartsViewModel.clearChosenParts();
    }
}
