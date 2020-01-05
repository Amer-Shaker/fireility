package com.lagorta.fireility;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import lombok.Builder;

@Builder
public final class FirebaseHelper {
    private static final String TAG = "FirebaseHelper";
    private static final String DEFAULT_NOTIFICATION_CHANNEL_ID = "fcm_default_channel";
    private static final String DEFAULT_NOTIFICATION_CHANNEL_NAME = "Weather";

    public void init(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                NotificationChannel notificationChannel = new NotificationChannel(DEFAULT_NOTIFICATION_CHANNEL_ID,
                        DEFAULT_NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
                notificationManager.createNotificationChannel(notificationChannel);
            } else {
                Log.e(TAG, "Failed to get the notification manager");
            }
        }

        Log.d(TAG, "Subscribing to test-library topic");
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic("test-library")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = context.getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = context.getString(R.string.msg_subscribe_failed);
                        }
                        Log.d(TAG, msg);
                    }
                });
        // [END subscribe_topics]
    }

//    public void logFcmToken() {
//        // Get token
//        // [START retrieve_current_token]
//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "getInstanceId failed", task.getException());
//                            return;
//                        }
//
//                        // Get new Instance ID token
//                        String token = task.getResult().getToken();
//
//                        // Log and toast
//                        String msg = mContext.getString(R.string.msg_token_fmt, token);
//                        Log.d(TAG, msg);
//                    }
//                });
//        // [END retrieve_current_token]
//    }
}
