package com.alvarodf.abysswalker.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An abstract class which represents an abstract entity or character of the game. It can be either friend or enemy.
 * It extends from the predefined 'Actor' class (LibGDX).
 * @author Alvaro de Francisco
 * @since January 21st, 2020
 */
public abstract class Entity extends Actor {

    protected int hp;
    protected int strength;
    protected int armor;

    protected TextureRegion textureRegion;
    protected Rectangle rectangle;

    /**
     * @param t
     */
    public void setTexture(Texture t) {

        textureRegion.setRegion(t);
        setSize(t.getWidth(), t.getHeight());
        rectangle.setSize(t.getWidth(), t.getHeight());

    }

    /**
     * @return
     */
    public Rectangle getRectangle() {

        rectangle.setPosition(getX(), getY());
        return rectangle;

    }

    /**
     * @param other
     * @return
     */
    public boolean overlaps(SampleEnemy other) { return this.getRectangle().overlaps(other.getRectangle()); }

    /**
     * @param dt
     */
    public void act(float dt) { super.act(dt); }

    /**
     * @param batch
     * @param parentAlpha
     */
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

        Color c = getColor(); // used to apply tint color effect
        batch.setColor(c.r, c.g, c.b, c.a);

        if (isVisible()) { batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation()); }

    }

}
