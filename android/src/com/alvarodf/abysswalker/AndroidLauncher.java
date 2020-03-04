package com.alvarodf.abysswalker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.alvarodf.abysswalker.services.ActivatedService;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * Launches the game in Android. Extends from AndroidApplication (LibGDX).
 * @since January 20th, 2020.
 * @author Alvaro de Francisco
 */
public final class AndroidLauncher extends AndroidApplication {

	/**
	 * Mandatory function that creates the Application. Initializes the game and the service.
	 * @param savedInstanceState Not used.
	 * @since January 20th, 2020.
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new AbysswalkerGame(), config);
		startService(new Intent(this, ActivatedService.class));

	}

    /**
     * Event function activated when the 'Back' Smartphone's button is touched. Returns to the Menu Activity.
     * @since March 4th, 2020.
     */
    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Exit to Menu")
                .setMessage("Are you sure you want to return to the menu?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    /**
                     * Event function activated when the 'Yes' option from the Dialog is clicked. Returns to the Menu Activity.
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
