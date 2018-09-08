package br.com.mathiasruck.notification;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MathiasruckFcmHandler extends FirebaseMessagingService {
    public static final int MESSAGE_NOTIFICATION_ID = 435345;
    public static final String CHANNEL_ID = "Notification_01";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Map<String, String> data = remoteMessage.getData();
        Log.i("Mruck.notifications", "Received message: " + notification.getBody());

        createNotification(notification);
    }


    // Creates notification based on title and body received
    private void createNotification(RemoteMessage.Notification notification) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.googleg_standard_color_18)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.Red))
                .setContentTitle(notification.getTitle() + "inside")
                .setContentText(notification.getBody() + "inside")
                .setBadgeIconType(Notification.BADGE_ICON_LARGE)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setAutoCancel(true);
        showNotification(mBuilder);


    }

    private void showNotification(NotificationCompat.Builder mBuilder) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // Issue the initial notification with zero progress
        int PROGRESS_MAX = 100;
        int PROGRESS_CURRENT = 0;
//        mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, true);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
    }
}