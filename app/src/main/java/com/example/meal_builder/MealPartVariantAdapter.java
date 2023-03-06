package com.example.meal_builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MealPartVariantAdapter extends ArrayAdapter<MealPart> {
    private final int layout;

    public MealPartVariantAdapter(Context context, int resource, List<MealPart> items) {
        super(context, R.layout.meal_part_template, items);
        this.layout = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = getContext();
        View view = LayoutInflater.from(context).inflate(this.layout, parent, false);

        TextView title = view.findViewById(R.id.part_title);
        TextView calories = view.findViewById(R.id.part_calories);
        TextView fats = view.findViewById(R.id.part_fats);
        TextView protein = view.findViewById(R.id.part_protein);
        TextView carbohydrates = view.findViewById(R.id.part_carbohydrates);
        EditText grams = view.findViewById(R.id.part_grams);
        ImageView image = view.findViewById(R.id.part_image);
        TextView totalCalories = view.findViewById(R.id.part_total_calories);
        TextView totalFats = view.findViewById(R.id.part_total_fats);
        TextView totalProtein = view.findViewById(R.id.part_total_protein);
        TextView totalCarbohydrates = view.findViewById(R.id.part_total_carbohydrates);

        MealPart item = getItem(position);

        title.setText(item.name);

        calories.setText(String.valueOf(item.calories));
        fats.setText(String.valueOf(item.fats));
        protein.setText(String.valueOf(item.protein));
        carbohydrates.setText(String.valueOf(item.carbohydrates));

        grams.setText(String.valueOf(item.grams));

        String uri = "@drawable/" + item.image;
        image.setImageResource(context.getResources().getIdentifier(uri, null, context.getPackageName()));

        totalCalories.setText(String.valueOf(item.getTotalCalories()));
        totalFats.setText(String.valueOf(item.getTotalFats()));
        totalProtein.setText(String.valueOf(item.getTotalProtein()));
        totalCarbohydrates.setText(String.valueOf(item.getTotalCarbohydrates()));

        return view;
    }
}

