package com.example.meal_builder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.meal_builder.databinding.ActivityMealEditBinding;

public class MealEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMealEditBinding binding = ActivityMealEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle args = getIntent().getExtras();
        String title = args.get("meal-title").toString();

        binding.mealTitle.setText(title);
    }

    public void onCancelBthClick(View view) {
        finish();
    }

    public void onSaveBtnClick(View view) {
        EditText title = (EditText) findViewById(R.id.meal_title);
        Intent intent = new Intent();
        intent.putExtra("edited-title", title.getText());
        setResult(RESULT_OK, intent);
        finish();
    }
}