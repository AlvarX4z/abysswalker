package com.alvarodf.abysswalker.sprites;

import com.alvarodf.abysswalker.screens.PlayScreen;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Alvaro de Francisco
 * @since February 25th, 2020
 */
public abstract class Enemy extends Sprite {

    protected World world;
    protected Screen screen;
    public Body body;

    /**
     *
     * @param screen
     * @param x
     * @param y
     * @since February 25th, 2020
     */
    public Enemy(PlayScreen screen, float x, float y) {

        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);

    }

    /**
     * @since February 25th, 2020
     */
    protected abstract void defineEnemy();

}
