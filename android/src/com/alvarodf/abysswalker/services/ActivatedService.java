package com.alvarodf.abysswalker.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * This class represent an Android Service with the goal of showing a toast when it's activated as a
 * project's requisite.
 * @since February 26th, 2020.
 * @author Alvaro de Francisco
 */
public final class ActivatedService extends Service {

    /**
     * Mandatory function for creating the Service.
     * @since February 26th, 2020.
     */
    @Override
    public void onCreate() { super.onCreate(); }

    /**
     * Returns the communication channel to the service.
     * @param intent The Intent that was used to bind to this service.
     * @return null because clients can not bind to the service.
     * @since February 26th, 2020.
     */
    @Override
    public IBinder onBind(Intent intent) { return null; }

    /**
     * Called by the system every time a client explicitly starts the service by calling Context.startService(Intent).
     * @param intent The Intent supplied to Context.startService(Intent).
     * @param flags  Additional data about this start request.
     * @param startId A unique integer representing this specific request to start.
     * @return A Not Sticky Service.
     * @since February 26th, 2020.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Activated!", Toast.LENGTH_LONG).show();
        return START_NOT_STICKY;

    }

}
