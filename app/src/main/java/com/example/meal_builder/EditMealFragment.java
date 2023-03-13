package com.example.meal_builder;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.meal_builder.databinding.MealPartTemplateBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class EditMealFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();

    public EditMealFragment() {
        super(R.layout.fragment_edit_meal);
    }

    static ArrayList<MealPart> parts = new ArrayList<MealPart>() {
        {
            add(new MealPart(ChoosePartsFragment.choosableParts.get(0)));
            add(new MealPart(ChoosePartsFragment.choosableParts.get(1)));
        }
    };


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String title = requireArguments().getString("title");

        TextView titleView = (TextView) getView().findViewById(R.id.meal_title);
        titleView.setText(title);

        ListView partsList = getView().findViewById(R.id.parts_container);
        partsList.setItemsCanFocus(true);
        MealPartVariantAdapter adapter = new MealPartVariantAdapter(getContext(), R.layout.meal_part_template, parts);
        partsList.setAdapter(adapter);

        Button cancelBtn = (Button) getView().findViewById(R.id.editCancelBtn);
        cancelBtn.setOnClickListener((cancelBtn1) -> {
            Navigation.findNavController(view).popBackStack();
        });

        Button saveBtn = (Button) getView().findViewById(R.id.editSaveBtn);
        saveBtn.setOnClickListener((saveBtn1) -> {
            Navigation.findNavController(view).getPreviousBackStackEntry().getSavedStateHandle().set("mealEdit", titleView.getText().toString());
            Navigation.findNavController(view).popBackStack();
        });

        Button chooseBtn = (Button) getView().findViewById(R.id.choosePartsBtn);
        chooseBtn.setOnClickListener((chooseBtn1) -> {
            Navigation.findNavController(view).navigate(R.id.action_edit_to_choose);
        });

//        getChildFragmentManager().setFragmentResultListener(
//                "partsChoise",
//                this,
//                (requestKey, result) -> {
//                    ArrayList<ChoosableMealPart> chosenParts = (ArrayList<ChoosableMealPart>) result.getSerializable("parts");
//                    for(ChoosableMealPart part : chosenParts) {
//                        parts.add(new MealPart(part));
//                    }
//
//                    adapter.notifyDataSetChanged();
//                });


        Navigation.findNavController(view)
                .getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("partsChoise")
                .observe(getViewLifecycleOwner(), (res) -> {
                    for(ChoosableMealPart part : (ArrayList<ChoosableMealPart>) res) {
                        parts.add(new MealPart(part));
                    }

                    adapter.notifyDataSetChanged();
                });
    }
}
