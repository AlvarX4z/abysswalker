package com.alvarodf.abysswalker.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Alvaro de Francisco
 * @since January 24th, 2020
 */
public class Vanyr extends Sprite {

    private World world;
    private Body body;

    /**
     * @since January 24th, 2020
     * @param world
     */
    public Vanyr(World world) {

        this.world = world;
        defineVanyr();

    }

    /**
     * @since January 24th, 2020
     */
    private void defineVanyr() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(100, 100);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circle = new CircleShape();

        circle.setRadius(50);

        fixtureDef.shape = circle;
        body.createFixture(fixtureDef);

    }

}
