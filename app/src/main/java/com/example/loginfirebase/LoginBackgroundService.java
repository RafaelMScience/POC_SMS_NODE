package com.example.loginfirebase;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class LoginBackgroundService extends Service {
    private ReceiveSms forceOfflineReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        // When service object is created, register a custom broadcast receiver.
        // So that this activity can process ACTION_FORCE_OFFLINE custom broadcast.
        forceOfflineReceiver = new ReceiveSms();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ReceiveSms.ACTION_FORCE_OFFLINE);
        registerReceiver(forceOfflineReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // When service object destroy, unregister the force offline receiver.
        if(forceOfflineReceiver!=null) {
            unregisterReceiver(forceOfflineReceiver);
            forceOfflineReceiver = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
