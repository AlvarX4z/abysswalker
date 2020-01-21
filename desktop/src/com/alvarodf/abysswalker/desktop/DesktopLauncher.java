package com.alvarodf.abysswalker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alvarodf.abysswalker.AbysswalkerGame;

/**
 *
 * @author Alvaro de Francisco
 * @since January 20th, 2020
 */
public final class DesktopLauncher {

	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AbysswalkerGame(), config);

		config.title = "Abysswalker";
		config.width = 800;
		config.height = 600;

	}

}
