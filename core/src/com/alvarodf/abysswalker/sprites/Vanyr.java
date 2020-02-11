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
 * @author Alvaro de Francisco
 * @since January 24th, 2020
 */
public final class Vanyr extends Sprite {

    public enum State { STANDING, JUMPING, FALLING, ATTACKING, RUNNING, DYING }
    private State currentState;
    private State previousState;

    private World world;
    public Body body;

    private Animation<TextureRegion> vanyrStanding;
    private Animation<TextureRegion> vanyrJumping;
    private Animation<TextureRegion> vanyrFalling;
    private Animation<TextureRegion> vanyrAttacking;
    private Animation<TextureRegion> vanyrRunning;
    private Animation<TextureRegion> vanyrDying;

    private float stateTimer;
    private boolean runningRight;

    /**
     * @since January 24th, 2020
     * @param world
     * @param screen
     */
    public Vanyr(World world, PlayScreen screen) {

        super(screen.getAtlas().findRegion("vanyr"));
        this.world = world;

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> standingFrames = new Array<>();
        Array<TextureRegion> jumpingFrames = new Array<>();
        Array<TextureRegion> fallingFrames = new Array<>();
        Array<TextureRegion> attackingFrames = new Array<>();
        Array<TextureRegion> runningFrames = new Array<>();
        Array<TextureRegion> dyingFrames = new Array<>();

        standingFrames.add(new TextureRegion(getTexture(), 0, 0, 28, 43));
        standingFrames.add(new TextureRegion(getTexture(), 30, 0, 28, 43));
        standingFrames.add(new TextureRegion(getTexture(), 60, 0, 30, 43));
        standingFrames.add(new TextureRegion(getTexture(), 91, 0, 27, 43));

        vanyrStanding = new Animation(0.13f, standingFrames);
        standingFrames.clear();

        attackingFrames.add(new TextureRegion(getTexture(), 0, 44, 27, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 28, 44, 33, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 62, 44, 80, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 143, 44, 63, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 208, 44, 38, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 247, 44, 34, 41));

        vanyrAttacking = new Animation(0.1f, attackingFrames);
        attackingFrames.clear();

        jumpingFrames.add(new TextureRegion(getTexture(), 0, 86, 36, 43));
        jumpingFrames.add(new TextureRegion(getTexture(), 40, 86, 38, 43));
        jumpingFrames.add(new TextureRegion(getTexture(), 79, 86, 37, 43));
        jumpingFrames.add(new TextureRegion(getTexture(), 117, 86, 35, 43));

        vanyrJumping = new Animation(0.05f, jumpingFrames);
        jumpingFrames.clear();

        fallingFrames.add(new TextureRegion(getTexture(), 117, 86, 35, 43));
        fallingFrames.add(new TextureRegion(getTexture(), 79, 86, 37, 43));
        fallingFrames.add(new TextureRegion(getTexture(), 40, 86, 38, 43));
        fallingFrames.add(new TextureRegion(getTexture(), 0, 86, 36, 43));

        vanyrFalling = new Animation(0.05f, fallingFrames);
        fallingFrames.clear();

        runningFrames.add(new TextureRegion(getTexture(), 0, 131, 46, 44));
        runningFrames.add(new TextureRegion(getTexture(), 48, 131, 39, 44));
        runningFrames.add(new TextureRegion(getTexture(), 88, 131, 41, 44));
        runningFrames.add(new TextureRegion(getTexture(), 130, 131, 36, 44));
        runningFrames.add(new TextureRegion(getTexture(), 166, 131, 37, 44));
        runningFrames.add(new TextureRegion(getTexture(), 203, 131, 47, 44));
        runningFrames.add(new TextureRegion(getTexture(), 251, 131, 44, 44));
        runningFrames.add(new TextureRegion(getTexture(), 295, 131, 39, 44));
        runningFrames.add(new TextureRegion(getTexture(), 334, 131, 42, 44));
        runningFrames.add(new TextureRegion(getTexture(), 377, 131, 35, 44));
        runningFrames.add(new TextureRegion(getTexture(), 413, 131, 36, 44));
        runningFrames.add(new TextureRegion(getTexture(), 450, 131, 48, 44));

        vanyrRunning = new Animation(0.08f, runningFrames);
        runningFrames.clear();

        dyingFrames.add(new TextureRegion(getTexture(), 0, 175, 34, 34));
        dyingFrames.add(new TextureRegion(getTexture(), 35, 175, 39, 34));
        dyingFrames.add(new TextureRegion(getTexture(), 74, 175, 39, 34));

        vanyrDying = new Animation(0.1f, dyingFrames);
        dyingFrames.clear();

        defineVanyr();
        setBounds(0, 0, 48, 48);

    }

    /**
     *
     * @param dt
     * @since February 8th, 2020
     */
    public void update(float dt) {

        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

    }

    /**
     *
     * @param dt
     * @return
     * @since February 10th, 2020
     */
    public TextureRegion getFrame(float dt) {

        currentState = getState();

        TextureRegion region;

        switch (currentState) {

            case JUMPING:
                region = vanyrJumping.getKeyFrame(stateTimer);
                break;

            case FALLING:
                region = vanyrFalling.getKeyFrame(stateTimer);
                break;

            case RUNNING:
                region = vanyrRunning.getKeyFrame(stateTimer, true);
                break;

            case STANDING:
                region = vanyrStanding.getKeyFrame(stateTimer, true);
                break;

            case ATTACKING:
                region = vanyrAttacking.getKeyFrame(stateTimer);
                break;

            case DYING:
                region = vanyrDying.getKeyFrame(stateTimer);
                break;

            default:
                region = vanyrStanding.getKeyFrame(stateTimer, true);
                break;

        }

        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {

            region.flip(true, false);
            runningRight = false;

        } else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {

            region.flip(true, false);
            runningRight = true;

        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;

        previousState = currentState;

        return region;

    }

    /**
     *
     * @return
     * @since February 10th, 2020
     */
    private State getState() {

        if (body.getLinearVelocity().y > 0) { return State.JUMPING; }
        else if (body.getLinearVelocity().y < 0) { return State.JUMPING; }
        else if (body.getLinearVelocity().x != 0) { return State.RUNNING; }
        else { return State.STANDING; }

    }

    /**
     * @since January 24th, 2020
     */
    private void defineVanyr() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(20, 24);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(16, 24);

        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);

    }

}
