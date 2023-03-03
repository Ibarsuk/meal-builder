package com.example.meal_builder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.DialogFragment;

import com.example.meal_builder.databinding.ActivityMainBinding;
import com.example.meal_builder.databinding.ActivityMealEditBinding;
import com.example.meal_builder.databinding.MealCardTemplateBinding;
import com.example.meal_builder.databinding.MealPartTemplateBinding;

import java.util.ArrayList;
import java.util.Objects;

public class EditMealFragment extends Fragment {
    public EditMealFragment() {
        super(R.layout.fragment_edit_meal);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String title = requireArguments().getString("title");


        TextView titleView = (TextView) getView().findViewById(R.id.meal_title);

        titleView.setText(title);

        Button cancelBtn = (Button) getView().findViewById(R.id.editCancelBtn);
        cancelBtn.setOnClickListener((cancelBtn1) -> {
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

        Button saveBtn = (Button) getView().findViewById(R.id.editSaveBtn);
        saveBtn.setOnClickListener((saveBtn1) -> {
            Bundle result = new Bundle();
            result.putString("title", titleView.getText().toString());
            getParentFragmentManager().setFragmentResult("mealEdit", result);
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

        Button chooseBtn = (Button) getView().findViewById(R.id.choosePartsBtn);
        chooseBtn.setOnClickListener((chooseBtn1) -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, ChoosePartsFragment.class, new Bundle())
                    .commit();
        });

        getParentFragmentManager().setFragmentResultListener("partsChoise", this, (requestKey, result) -> {
            for(String partName : result.getStringArrayList("parts")) {
                MealPartTemplateBinding cardTemplatebinding = MealPartTemplateBinding.inflate(
                        getLayoutInflater(), getView().findViewById(R.id.parts_container), true
                );

                cardTemplatebinding.cardExampleTitle.setText(partName);
            }
        });
    }
}
