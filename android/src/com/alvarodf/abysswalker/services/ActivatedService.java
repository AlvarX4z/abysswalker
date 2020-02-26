package com.alvarodf.abysswalker.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * @since February 26th, 2020
 */
public final class ActivatedService extends Service {

    /**
     * @since February 26th, 2020
     */
    @Override
    public void onCreate() { super.onCreate(); }

    /**
     *
     * @param intent
     * @return
     * @since February 26th, 2020
     */
    @Override
    public IBinder onBind(Intent intent) { return null; }

    /**
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     * @since February 26th, 2020
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Activated!", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;

    }

}
