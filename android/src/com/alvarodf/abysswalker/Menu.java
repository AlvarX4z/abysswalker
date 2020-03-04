package com.alvarodf.abysswalker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Launching Activity for Android. Shows the menu with a background and a 'Play!' button. Extends from AppCompatActivity.
 * @since February 26th, 2020.
 * @author Alvaro de Francisco
 */
public final class Menu extends AppCompatActivity {

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

    /**
     * Event function activated when the 'Back' Smartphone's button is touched. Exits the game.
     * @since March 4th, 2020.
     */
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Exit Game")
                .setMessage("Are you sure you want to close the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    /**
                     * Event function activated when the 'Yes' option from the Dialog is clicked. Exits the game.
                     * @param dialog The dialog that received the click.
                     * @param which The button that was clicked.
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) { finish(); }

                })
                .setNegativeButton("No", null)
                .show();

    }

}
