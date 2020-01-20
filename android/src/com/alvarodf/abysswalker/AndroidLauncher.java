package com.alvarodf.abysswalker;

import android.app.Activity;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 *
 * @author Alvaro de Francisco
 * @since January 20th, 2020
 */
public class AndroidLauncher extends Activity {

	@Override
	protected void onCreate (Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new AbysswalkerGame(), config);

	}

}
