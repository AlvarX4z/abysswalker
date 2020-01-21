package com.alvarodf.abysswalker.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author Alvaro de Francisco
 * @since January 21st, 2020
 */
public final class SampleEnemy extends Enemy {

    private TextureRegion textureRegion;
    private Rectangle rectangle;

    /**
     *
     */
    public SampleEnemy() {

        super();
        this.hp = 10;
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();

    }

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
    public boolean overlaps(SampleEnemy other) {
        return this.getRectangle().overlaps(other.getRectangle());
    }

    /**
     * @param dt
     */
    public void act(float dt) {
        super.act(dt);
    }

    /**
     * @param batch
     * @param parentAlpha
     */
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Color c = getColor(); // used to apply tint color effect
        batch.setColor(c.r, c.g, c.b, c.a);
        if (isVisible())
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(),
                    getHeight(), getScaleX(), getScaleY(), getRotation());
    }

}
