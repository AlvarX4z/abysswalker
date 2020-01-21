package com.alvarodf.abysswalker.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author Alvaro de Francisco
 * @since January 21st, 2020
 */
public final class Vanyr extends Entity {

    private int level;
    private int xp;

    private Texture imageHealthy;
    private Texture imageDamaged;
    private Texture imageDead;

    /**
     * @since January 21st, 2020
     */
    public Vanyr() { super(); this.hp = 50; }

    /**
     * @since January 21st, 2020
     * @return
     */
    public Texture getImageHealthy() { return imageHealthy; }

    /**
     * @since January 21st, 2020
     * @param image
     */
    public void setImageHealthy(Texture image) { this.imageHealthy = image; }

    /**
     * @since January 21st, 2020
     * @param b
     */
    public void draw(Batch b) {

        if (hp > 50) { b.draw(imageHealthy, getX(), getY()); }
        else if (hp > 0 && hp <= 50) { b.draw(imageDamaged, getX(), getY()); }
        else { b.draw(imageDead, getX(), getY()); }

    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            this.moveBy(-1,0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            this.moveBy(1,0);
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            this.moveBy(0,1);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            this.moveBy(0,-1);

    }
}
