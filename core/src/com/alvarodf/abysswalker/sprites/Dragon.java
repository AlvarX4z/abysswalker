package com.alvarodf.abysswalker.sprites;

import com.alvarodf.abysswalker.screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Represents the enemy Dragon. Extends from the Sprite class (LibGDX).
 * @since February 25th, 2020.
 * @author Alvaro de Francisco
 */
public final class Dragon extends Sprite {

    private World world; // Physics
    public Body body; // Dragon's body for physics within the world

    private Animation<TextureRegion> dragonStanding; // Dragon's standing animation. Not working

    private float stateTimer; // Helper for transiting from one animation frame to other
    public int dragonHp; // Dragon's HP

    /**
     * Dragon's constructor. Must be invoked when creating the PlayScreen.
     * @param screen PlayScreen.
     * @since January 24th, 2020.
     */
    public Dragon(PlayScreen screen) {

        super(screen.getDragonAtlas().findRegion("dragon"));
        this.world = screen.getWorld();

        dragonHp = 100;
        stateTimer = 0;

        Array<TextureRegion> standingFrames = new Array<>();

        standingFrames.add(new TextureRegion(getTexture(), 0, 0, 172, 102));
        standingFrames.add(new TextureRegion(getTexture(), 173, 0, 170, 102));
        standingFrames.add(new TextureRegion(getTexture(), 344, 0, 175, 102));

        dragonStanding = new Animation(0.13f, standingFrames);
        standingFrames.clear();

        defineDragon();
        setBounds(0, 0, 172, 102);

    }

    /**
     * Updates the dragon's position when it moves.
     * @param dt Delta Time.
     * @since February 8th, 2020.
     */
    public void update(float dt) {

        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(dragonStanding.getKeyFrame(stateTimer, true));

    }

    /**
     * Dragon's physical definition.
     * @since February 25th, 2020.
     */
    protected void defineDragon() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(1300, 50);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(85, 50);

        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);

    }

    /**
     * Getter for the Dragon's body.
     * @return Dragon's body.
     * @since February 25th, 2020.
     */
    public Body getBody() { return body; }

}
