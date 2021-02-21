package com.aakash.assignmentone;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class broadcastForReminder extends BroadcastReceiver {
    static MediaPlayer mp;
    static final String chID = "Reminder";
    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.alarm_beep);
        mp.start();

        String msg = intent.getStringExtra("rMsg");
        if(intent.getStringExtra("rMsg") != null && intent.getStringExtra("rMsg").equals(msg)){
            NotificationManager notMan = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String name = "REMINDER NOTIFICATION";
                String description = "Notification channel for notifying while reminder beeps.";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel(chID, name, importance);
                channel.setDescription(description);
                notMan.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, chID)
                    .setSmallIcon(R.drawable.ic_baseline_alarm_24)
                    .setContentTitle("It's time!")
                    .setContentText(msg)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
            Intent i = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_ONE_SHOT);
            builder.setContentIntent(pendingIntent);
            notMan.notify(1, builder.build());
        }
    }
}
