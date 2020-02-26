package com.alvarodf.abysswalker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alvarodf.abysswalker.AbysswalkerGame;

/**
 * Mandatory class in order to get the game working on Desktop.
 * @since January 20th, 2020.
 * @author Alvaro de Francisco
 */
public final class DesktopLauncher {

	/**
	 * Mandatory function in order to get the game working on Desktop.
	 * @param arg DesktopLauncher statements.
	 * @since January 20th, 2020.
	 */
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AbysswalkerGame(), config);

		config.title = "Abysswalker";
		config.foregroundFPS = 60;
		config.width = 800;
		config.height = 600;

	}

}
