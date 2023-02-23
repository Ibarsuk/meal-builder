package com.example.meal_builder;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meal_builder.databinding.ActivityMainBinding;
import com.example.meal_builder.databinding.MealCardExampleBinding;
import com.example.meal_builder.databinding.MealCardTemplateBinding;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    ActivityResultLauncher<Intent> EditLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() != RESULT_OK) {
                    return;
                }
                TextView title = (TextView) findViewById(R.id.card_example_title);
                title.setText(result.getData().getExtras().get("edited-title").toString());
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onCreate");

        ImageView cardImg = (ImageView) findViewById(R.id.card_example_img);
        cardImg.setImageResource(R.drawable.breakfast1);

        TextView cardTitle = (TextView) findViewById(R.id.card_example_title);
        cardTitle.setText("Мой завтрак");


        MealCardTemplateBinding cardTemplatebinding = MealCardTemplateBinding.inflate(getLayoutInflater(), binding.cardsContainer, true);
        cardTemplatebinding.mealCardImg.setImageResource(R.drawable.sandwitch);
        cardTemplatebinding.mealCardTitle.setText("Мой перекус");
        cardTemplatebinding.mealCardCalories.setText("341");
        cardTemplatebinding.mealCardFats.setText("23");
        cardTemplatebinding.mealCardProtein.setText("156");
        cardTemplatebinding.mealCardCarbonhydrates.setText("64");
        cardTemplatebinding.mealCardParts.setImageResource(R.drawable.salad);

        Button addMealBtn = (Button) findViewById(R.id.add_meal_btn);

        addMealBtn.setOnClickListener(addMealBtn1 -> Log.i(TAG, "Add meal btn pressed (programmatically processed)"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStart");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        Log.w(TAG, "onDestroy");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        Log.v(TAG, "onPause");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume");
    }

    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onRestart");
    }

    public void onExampleCardClick(View view) {
        Log.i(TAG, "Add meal btn pressed (declaratively processed)");

        Intent intent = new Intent(this, MealEdit.class);
        MealCardExampleBinding binding = MealCardExampleBinding.bind(view);
        intent.putExtra("meal-title", binding.cardExampleTitle.getText().toString());

        EditLauncher.launch(intent);
    }
}