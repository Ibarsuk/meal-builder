package com.example.meal_builder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meal_builder.databinding.ActivityMainBinding;
import com.example.meal_builder.databinding.FragmentMealsBinding;
import com.example.meal_builder.databinding.MealCardExampleBinding;
import com.example.meal_builder.databinding.MealCardTemplateBinding;

public class MealsFragment extends Fragment {
    public MealsFragment() {
        super(R.layout.fragment_meals);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FragmentMealsBinding binding = FragmentMealsBinding.bind(view);

        ImageView cardImg = (ImageView) view.findViewById(R.id.card_example_img);
        cardImg.setImageResource(R.drawable.breakfast1);

        TextView cardTitle = (TextView) view.findViewById(R.id.card_example_title);
        cardTitle.setText("Мой завтрак");

        MealCardTemplateBinding cardTemplatebinding = MealCardTemplateBinding.inflate(
                getLayoutInflater(), binding.cardsContainer, true
        );
        cardTemplatebinding.mealCardImg.setImageResource(R.drawable.sandwitch);
        cardTemplatebinding.mealCardTitle.setText("Мой перекус");
        cardTemplatebinding.mealCardCalories.setText("341");
        cardTemplatebinding.mealCardFats.setText("23");
        cardTemplatebinding.mealCardProtein.setText("156");
        cardTemplatebinding.mealCardCarbonhydrates.setText("64");
        cardTemplatebinding.mealCardParts.setImageResource(R.drawable.salad);

        Navigation.findNavController(view)
                .getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("mealEdit")
                .observe(getViewLifecycleOwner(), (res) -> cardTitle.setText(res.toString()));

        cardImg.setOnClickListener((img) -> {
            Bundle bundle = new Bundle();
            bundle.putString("title",  cardTitle.getText().toString());

            Navigation.findNavController(view).navigate(R.id.action_meals_to_edit, bundle);
        });

    }
}