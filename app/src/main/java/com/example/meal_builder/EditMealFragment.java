package com.example.meal_builder;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.meal_builder.databinding.MealPartTemplateBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class EditMealFragment extends Fragment {
    private final String TAG = this.getClass().getSimpleName();
    private  String title;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    this.showNotification();
                }
            });

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
        this.title = title;

        TextView titleView = (TextView) getView().findViewById(R.id.meal_title);
        titleView.setText(this.title);

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
            Navigation.findNavController(view)
                    .getPreviousBackStackEntry()
                    .getSavedStateHandle()
                    .set("mealEdit", titleView.getText().toString());

            Navigation.findNavController(view).popBackStack();
        });

        Button chooseBtn = (Button) getView().findViewById(R.id.choosePartsBtn);
        chooseBtn.setOnClickListener((chooseBtn1) -> {
            Navigation.findNavController(view).navigate(R.id.action_edit_to_choose);
        });

        Button planBtn = (Button) getView().findViewById(R.id.editPlanButton);
        planBtn.setOnClickListener((saveBtn1) -> {
            this.showNotification();
        });

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

    private void showNotification() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getContext());
        if (notificationManager.areNotificationsEnabled()) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), "plan_channel")
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle(getResources().getString(R.string.plan_notification_title) + " " + this.title);

            notificationManager.notify(1, builder.build());
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }
}
