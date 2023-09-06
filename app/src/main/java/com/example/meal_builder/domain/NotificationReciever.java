package com.example.meal_builder.domain;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.meal_builder.R;
import com.example.meal_builder.ui.view.MainActivity;

public class NotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (notificationManager.areNotificationsEnabled()) {

            Intent notificationIntent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context,
                    0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "plan_channel")
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentText("На это время вы запланировали" + " " + intent.getStringExtra("title"))
                    .setContentTitle("Время есть" + " " + intent.getStringExtra("title"))
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true);

            notificationManager.notify(1, builder.build());
        }
    }
}
