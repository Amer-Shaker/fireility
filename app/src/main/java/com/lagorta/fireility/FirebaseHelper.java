package com.lagorta.fireility;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public final class FirebaseHelper {
    private static final String TAG = "FirebaseHelper";
    private final Context mContext;

    public FirebaseHelper(final Context context) {
        mContext = context;
        configureFirebaseChannel();
    }

    private void configureFirebaseChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId = mContext.getString(R.string.default_notification_channel_id);
            String channelName = mContext.getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        Log.d(TAG, "Subscribing to test-library topic");
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic("test-library")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = mContext.getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = mContext.getString(R.string.msg_subscribe_failed);
                        }
                        Log.d(TAG, msg);
                    }
                });
        // [END subscribe_topics]
    }

    public void logFcmToken() {
        // Get token
        // [START retrieve_current_token]
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = mContext.getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                    }
                });
        // [END retrieve_current_token]
    }

    public void showToast() {
        Toast.makeText(mContext, "This is amer toast", Toast.LENGTH_SHORT).show();
    }
}
