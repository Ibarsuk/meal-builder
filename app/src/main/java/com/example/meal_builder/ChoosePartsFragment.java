package com.example.meal_builder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meal_builder.databinding.MealPartTemplateBinding;
import com.example.meal_builder.databinding.MealPartVariantBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class ChoosePartsFragment extends Fragment implements DefaultLifecycleObserver {
    static ArrayList<ChoosableMealPart> choosableParts = new ArrayList<ChoosableMealPart>(){
        {
            add(new ChoosableMealPart(123, 421, 333, 44, "Сыр", "salad"));
            add(new ChoosableMealPart(123, 421, 333, 44, "Колбаса", "tomatoes"));

            for (int i = 0; i < 200; i++) {
                add(new ChoosableMealPart(123, 421, 333, 44, "Сыр", "salad"));
            }
        }
    };

    private final String TAG = this.getClass().getSimpleName();
    ArrayList<ChoosableMealPart> chosenParts = new ArrayList<ChoosableMealPart>();
    ChosenPartsService partsService = new ChosenPartsService();

    public ChoosePartsFragment() {
        super(R.layout.fragment_choose_parts);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated");
        Toast.makeText(getContext(), "onViewCreated", Toast.LENGTH_SHORT).show();

        RecyclerView partsList = getView().findViewById(R.id.part_variants_container);
        MealPartRecyclerAdapter adapter = new MealPartRecyclerAdapter(getContext(), choosableParts, partsService);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        partsList.setLayoutManager(manager);
        partsList.setAdapter(adapter);

        Button cancelBtn = (Button) getView().findViewById(R.id.chooseCancelBtn);
        cancelBtn.setOnClickListener((cancelBtn1) -> {
            Navigation.findNavController(view).popBackStack();
        });

        Button saveBtn = (Button) getView().findViewById(R.id.chooseSaveBtn);
        saveBtn.setOnClickListener((saveBtn1) -> {
            Navigation.findNavController(view).getPreviousBackStackEntry().getSavedStateHandle().set("partsChoise", partsService.getParts());
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
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        Log.i(TAG, "onViewStateRestored");
        Toast.makeText(getContext(), "onViewStateRestored", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");
        Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
        Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG, "onPause");
        Toast.makeText(getContext(), "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i(TAG, "onStop");
        Toast.makeText(getContext(), "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState");
        Toast.makeText(getContext(), "onSaveInstanceState", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.i(TAG, "onDestroyView");
        Toast.makeText(getContext(), "onDestroyView", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy");
        Toast.makeText(getContext(), "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.i(TAG, "onDetach");
        Toast.makeText(getContext(), "onDetach", Toast.LENGTH_SHORT).show();
    }
}
