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
 *
 * @author Alvaro de Francisco
 * @since February 25th, 2020
 */
public final class Dragon extends Sprite {

    private World world;
    public Body body;

    private Animation<TextureRegion> dragonStanding;

    private float stateTimer;

    /**
     *
     * @since January 24th, 2020
     * @param screen
     */
    public Dragon(PlayScreen screen) {

        super(screen.getDragonAtlas().findRegion("dragon"));
        this.world = screen.getWorld();

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
     *
     * @param dt
     * @since February 8th, 2020
     */
    public void update(float dt) {

        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        // setRegion(getFrame(dt));
        setRegion(dragonStanding.getKeyFrame(stateTimer, true));

    }

    /**
     *
     * @param dt
     * @return
     * @since February 10th, 2020
     */
    public TextureRegion getFrame(float dt) {

        TextureRegion region = dragonStanding.getKeyFrame(stateTimer, true);

        if ((body.getLinearVelocity().x < 0) && !region.isFlipX()) {

            region.flip(true, false);

        } else if ((body.getLinearVelocity().x > 0) && region.isFlipX()) {

            region.flip(true, false);

        }

        return region;

    }

    /**
     * @since February 25th, 2020
     */
    protected void defineDragon() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(250, 150);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(85, 50);

        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);

    }

}
