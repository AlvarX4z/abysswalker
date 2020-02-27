package com.alvarodf.abysswalker;

import com.alvarodf.abysswalker.screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * The game's core class. Here can be found basic parameters for the game and where to create, render and dispose elements.
 * Extends from the predefined 'Game' class (LibGDX).
 * @since January 20th, 2020.
 * @author Alvaro de Francisco
 */
public final class AbysswalkerGame extends Game {

    public final static int VIEWPORT_WIDTH = 400; // Viewport's width (pixels)
    public final static int VIEWPORT_HEIGHT = 208; // Viewport's height (pixels)

    public SpriteBatch batch; // Allocated Sprites to be created and rendered

    public static AssetManager manager; // Helps managing game's assets

    /**
     * Mandatory function from LibGDX for creating the basic elements of the game.
     * @since January 20th, 2020.
     */
    @Override
    public void create() {

        batch = new SpriteBatch();
        manager = new AssetManager();

        manager.load("audio/main_theme.mp3", Music.class);
        // manager.load("String sonido", Sound.class);
        manager.load("sprites/dragon.pack", TextureAtlas.class);
        manager.load("sprites/vanyr.pack", TextureAtlas.class);
        manager.finishLoading();

        setScreen(new PlayScreen(this));

    }

    /**
     * Mandatory function from LibGDX for rendering the basic elements of the game.
     * @since January 20th, 2020.
     */
    @Override
    public void render() { super.render(); }

    /**
     * Mandatory function from LibGDX for disposing the basic elements of the game.
     * @since January 20th, 2020.
     */
    @Override
    public void dispose() {

        super.dispose();
        batch.dispose();
        manager.dispose();

    }

}
