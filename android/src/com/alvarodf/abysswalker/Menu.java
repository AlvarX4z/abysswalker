package com.alvarodf.abysswalker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Launching Activity for Android. Shows the menu with a background and a 'Play!' button. Extends from AppCompatActivity.
 * @since February 26th, 2020.
 * @author Alvaro de Francisco
 */
public class Menu extends AppCompatActivity {

    /**
     * Mandatory function that creates the Activity. The ContentView is set to 'activity_menu'.
     * @param savedInstanceState Not used.
     * @since February 26th, 2020.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_menu);

    }

    /**
     * Event function activated when the 'Play!' button is touched. Launches the game and the service.
     * @param view Not used.
     * @since February 26th, 2020.
     */
    public void play(View view) {

        Intent i = new Intent(this, AndroidLauncher.class);
        this.startActivity(i);

    }

}
