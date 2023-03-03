package com.example.meal_builder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.meal_builder.databinding.MealPartTemplateBinding;
import com.example.meal_builder.databinding.MealPartVariantBinding;

import java.util.ArrayList;

public class ChoosePartsFragment extends Fragment {
    String[] names = new String[] {"Сыр", "Колбаса"};
    ArrayList<String> chosenParts = new ArrayList<String>();

    public ChoosePartsFragment() {
        super(R.layout.fragment_choose_parts);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button cancelBtn = (Button) getView().findViewById(R.id.chooseCancelBtn);
        cancelBtn.setOnClickListener((cancelBtn1) -> {
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

        Button saveBtn = (Button) getView().findViewById(R.id.chooseSaveBtn);
        saveBtn.setOnClickListener((saveBtn1) -> {
            Bundle result = new Bundle();
            result.putStringArrayList("parts", chosenParts);
            getParentFragmentManager().setFragmentResult("partsChoise", result);
            requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        });

        for(String partName : names) {
            MealPartVariantBinding cardTemplatebinding = MealPartVariantBinding.inflate(
                    getLayoutInflater(), getView().findViewById(R.id.part_variants_container), true
            );

            cardTemplatebinding.cardExampleTitle.setText(partName);
            cardTemplatebinding.partVariant.setOnClickListener((card) -> {
                TextView cardTitle = (TextView) card.findViewById(R.id.card_example_title);
                chosenParts.add(cardTitle.getText().toString());
            });
        }
    }
}
