package com.alvarodf.abysswalker;

import android.content.Intent;
import android.os.Bundle;

import com.alvarodf.abysswalker.services.ActivatedService;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 *
 * @author Alvaro de Francisco
 * @since January 20th, 2020
 */
public final class AndroidLauncher extends AndroidApplication {

	/**
	 *
	 * @param savedInstanceState
	 * @since January 20th, 2020
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new AbysswalkerGame(), config);
		Intent intent = new Intent(this, ActivatedService.class);
		startService(intent);

	}

}
