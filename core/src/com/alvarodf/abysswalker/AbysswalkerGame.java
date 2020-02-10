package com.alvarodf.abysswalker;

import com.alvarodf.abysswalker.screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The game's core class. Here can be found basic parameters for the game and where to create, render and dispose elements.
 * Extends from the predefined 'Game' class (LibGDX).
 * @author Alvaro de Francisco
 * @since January 20th, 2020
 */
public final class AbysswalkerGame extends Game {

    public final static int VIEWPORT_WIDTH = 400; // Viewport's width (pixels)
    public final static int VIEWPORT_HEIGHT = 208; // Viewport's height (pixels)

    public SpriteBatch batch; // Allocated Sprites to be created and rendered

    /**
     * Mandatory function from LibGDX for creating the basic elements of the game.
     * @since January 20th, 2020
     */
    @Override
    public void create() {

        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));

    }

    /**
     * Mandatory function from LibGDX for rendering the basic elements of the game.
     * @since January 20th, 2020
     */
    @Override
    public void render() { super.render(); }

    /**
     * Mandatory function from LibGDX for disposing the basic elements of the game.
     * @since January 20th, 2020
     */
    @Override
    public void dispose() { batch.dispose(); }

}
