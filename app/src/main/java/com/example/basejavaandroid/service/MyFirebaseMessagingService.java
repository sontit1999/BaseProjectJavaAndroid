package com.example.basejavaandroid.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.basejavaandroid.BookingActivity;
import com.example.basejavaandroid.MainActivity;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.base.Constant;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // handle a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    private void sendNotification(String messageBody) {
        if(isAppVisible()){
            Intent intent = new Intent(Constant.ACTION_SHOW_DIALOG_REVIEW);
            intent.putExtra(BookingActivity.PARAM_NAME_VOUCHER,"Voucher mĩ phẩm");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
            return;
        }
        Intent intent = new Intent(this, BookingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                        .setContentTitle(getString(R.string.notification_channel_id))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .addAction(new NotificationCompat.Action(
                                android.R.drawable.sym_call_missed,
                                "Cancel",
                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)))
                        .addAction(new NotificationCompat.Action(
                                android.R.drawable.sym_call_outgoing,
                                "OK",
                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)));

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }
    boolean isAppVisible() {
        return ProcessLifecycleOwner
                .get()
                .getLifecycle()
                .getCurrentState()
                .isAtLeast(Lifecycle.State.STARTED);
    }
}

