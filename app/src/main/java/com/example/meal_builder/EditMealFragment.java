package com.example.meal_builder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.meal_builder.databinding.MealPartTemplateBinding;

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
            getParentFragmentManager().beginTransaction().remove(this).commit();
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
            getChildFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_choose, ChoosePartsFragment.class, new Bundle())
                    .commit();
        });

        getChildFragmentManager().setFragmentResultListener(
                "partsChoise",
                this,
                (requestKey, result) -> {
                    for(String partName : result.getStringArrayList("parts")) {
                        MealPartTemplateBinding cardTemplatebinding = MealPartTemplateBinding.inflate(
                                getLayoutInflater(), getView().findViewById(R.id.parts_container), true
                        );

                        cardTemplatebinding.cardExampleTitle.setText(partName);
                    }
                });
    }
}
