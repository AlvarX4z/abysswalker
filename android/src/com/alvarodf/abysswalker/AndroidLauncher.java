package com.alvarodf.abysswalker;

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

}
