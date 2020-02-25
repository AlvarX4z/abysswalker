package com.alvarodf.abysswalker.sprites;

import com.alvarodf.abysswalker.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 *
 * @author Alvaro de Francisco
 * @since February 25th, 2020
 */
public final class Dragon extends Enemy {

    private float stateTime;
    private Animation<TextureRegion> dragonStanding;


    /**
     * @param screen
     * @param x
     * @param y
     * @since February 25th, 2020
     */
    public Dragon(PlayScreen screen, float x, float y) {
        super(screen, x, y);
    }

    @Override
    protected void defineEnemy() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(20, 24);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape rectangle = new PolygonShape();

        // rectangle.setAsBox(16, 24);

        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);

    }

}
