package com.example.meal_builder.ui.adapters;

        import android.content.Context;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.meal_builder.data.model.ChoosableMealPart;
        import com.example.meal_builder.data.model.MealPart;
        import com.example.meal_builder.data.model.UserMeal;
        import com.example.meal_builder.ui.viewmodels.MealPartsViewModel;
        import com.example.meal_builder.R;
        import com.example.meal_builder.ui.viewmodels.MealsViewModel;

        import java.util.List;

public class MealPartVariantRecyclerAdapter extends RecyclerView.Adapter<MealPartVariantRecyclerAdapter.ViewHolder>{
    private final String TAG = this.getClass().getSimpleName();
    private final LayoutInflater inflater;
    public List<MealPart> items;
    private Context context;


    MealsViewModel mealsViewModel;
    UserMeal meal;


    public MealPartVariantRecyclerAdapter(Context context, UserMeal meal, MealsViewModel mealsViewModel) {
        this.context = context;
        this.items = meal.parts;
        this.inflater = LayoutInflater.from(context);
        this.mealsViewModel = mealsViewModel;
        this.meal = meal;
    }

    @Override
    public MealPartVariantRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.meal_part_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MealPartVariantRecyclerAdapter.ViewHolder holder, int position) {
        MealPart item = items.get(position);

        holder.grams.setText(String.valueOf(item.grams));

        holder.title.setText(item.name);

        holder.calories.setText(String.valueOf(item.calories));
        holder.fats.setText(String.valueOf(item.fats));
        holder.protein.setText(String.valueOf(item.protein));
        holder.carbohydrates.setText(String.valueOf(item.carbohydrates));

        String uri = "@drawable/" + item.image;
        holder.image.setImageResource(context.getResources().getIdentifier(uri, null, context.getPackageName()));

        holder.totalCalories.setText(String.valueOf(item.getTotalCalories()));
        holder.totalFats.setText(String.valueOf(item.getTotalFats()));
        holder.totalProtein.setText(String.valueOf(item.getTotalProtein()));
        holder.totalCarbohydrates.setText(String.valueOf(item.getTotalCarbohydrates()));
        holder.grams.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                final EditText gramsView = (EditText) v;
                item.grams = Integer.parseInt(gramsView.getText().toString());
                this.notifyItemChanged(position);
                mealsViewModel.changeEditingMeal(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView calories;
        TextView fats;
        TextView protein;
        TextView carbohydrates;
        EditText grams;
        ImageView image;
        TextView totalCalories;
        TextView totalFats;
        TextView totalProtein;
        TextView totalCarbohydrates;

        LinearLayout partsLayout;
        int id;
        ViewHolder(View view){
            super(view);

            title = view.findViewById(R.id.part_title);
            calories = view.findViewById(R.id.part_calories);
            fats = view.findViewById(R.id.part_fats);
            protein = view.findViewById(R.id.part_protein);
            carbohydrates = view.findViewById(R.id.part_carbohydrates);
            grams = view.findViewById(R.id.part_grams);
            image = view.findViewById(R.id.part_image);
            totalCalories = view.findViewById(R.id.part_total_calories);
            totalFats = view.findViewById(R.id.part_total_fats);
            totalProtein = view.findViewById(R.id.part_total_protein);
            totalCarbohydrates = view.findViewById(R.id.part_total_carbohydrates);
            partsLayout = view.findViewById(R.id.parts_layout);
        }
    }
}

