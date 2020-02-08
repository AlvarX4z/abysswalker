package com.alvarodf.abysswalker.sprites;

import com.alvarodf.abysswalker.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Alvaro de Francisco
 * @since January 24th, 2020
 */
public class Vanyr extends Sprite {

    private World world;
    public Body body;
    private TextureRegion vanyrStand;

    /**
     * @since January 24th, 2020
     * @param world
     */
    public Vanyr(World world, PlayScreen screen) {

        super(screen.getAtlas().findRegion("khorne - copia"));
        this.world = world;
        defineVanyr();
        vanyrStand = new TextureRegion(getTexture(), 0, 0, 48, 48);
        setBounds(0, 0, 48, 48);
        setRegion(vanyrStand);

    }

    public void update(float dt) { setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2); }

    /**
     * @since January 24th, 2020
     */
    private void defineVanyr() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(100, 200);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(20, 25);

        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);

    }

}
