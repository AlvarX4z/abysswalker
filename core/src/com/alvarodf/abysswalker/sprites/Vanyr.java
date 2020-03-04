package com.alvarodf.abysswalker.sprites;

import com.alvarodf.abysswalker.screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
 * Represents Vanyr. Extends from the Sprite class (LibGDX).
 * @since January 24th, 2020.
 * @author Alvaro de Francisco
 */
public final class Vanyr extends Sprite  {

    public enum State { STANDING, JUMPING, FALLING, ATTACKING, RUNNING, DYING } // Represents Vanyr's state
    public State currentState; // Represents Vanyr's current state
    private State previousState; // Represents Vanyr's previous state



    private World world; // Physics
    public Body body; // Vanyr's body for physics within the world

    private Animation<TextureRegion> vanyrStanding; // Vanyr's standing animation
    private Animation<TextureRegion> vanyrJumping; // Vanyr's jumping animation
    private Animation<TextureRegion> vanyrFalling; // Vanyr's falling animation
    private Animation<TextureRegion> vanyrAttacking; // Vanyr's attacking animation
    private Animation<TextureRegion> vanyrRunning; // Vanyr's running animation
    private Animation<TextureRegion> vanyrDying; // Vanyr's dying animation

    private float stateTimer; // Helper for transiting from one animation frame to other
    private boolean runningRight; // True if Vanyr's moving to the right, false to the left

    /**
     * Vanyr's constructor. Must be invoked when creating the PlayScreen.
     * @param screen PlayScreen.
     * @since January 24th, 2020.
     */
    public Vanyr(PlayScreen screen) {

        super(screen.getVanyrAtlas().findRegion("vanyr"));
        this.world = screen.getWorld();

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

        attackingFrames.add(new TextureRegion(getTexture(), 0, 45, 27, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 29, 45, 33, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 63, 45, 80, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 144, 45, 63, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 208, 45, 38, 41));
        attackingFrames.add(new TextureRegion(getTexture(), 248, 45, 34, 41));

        vanyrAttacking = new Animation(0.05f, attackingFrames);
        attackingFrames.clear();

        jumpingFrames.add(new TextureRegion(getTexture(), 0, 86, 36, 43));
        jumpingFrames.add(new TextureRegion(getTexture(), 40, 86, 38, 43));
        jumpingFrames.add(new TextureRegion(getTexture(), 79, 86, 37, 43));
        jumpingFrames.add(new TextureRegion(getTexture(), 117, 86, 35, 43));

        vanyrJumping = new Animation(0.2f, jumpingFrames);
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
        dyingFrames.add(new TextureRegion(getTexture(), 36, 176, 39, 34));
        dyingFrames.add(new TextureRegion(getTexture(), 76, 176, 39, 34));

        vanyrDying = new Animation(0.1f, dyingFrames);
        dyingFrames.clear();

        defineVanyr();
        setBounds(0, 0, 48, 48);

    }

    /**
     * Updates Vanyr's position when he moves.
     * @param dt Delta Time.
     * @since February 8th, 2020.
     */
    public void update(float dt) {

        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

    }

    /**
     * Getter for Vanyr's animation frame with the help of the 'stateTimer' variable.
     * @param dt Delta Time.
     * @return The corresponding animation frame updated by each delta time.
     * @since February 10th, 2020.
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

        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) { // Flips when running left

            region.flip(true, false);
            runningRight = false;

        } else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) { // Flips when running right

            region.flip(true, false);
            runningRight = true;

        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;

        previousState = currentState;

        return region;

    }

    /**
     * Getter for Vanyr's enum state.
     * @return Vanyr's state at the moment as an enum.
     * @since February 10th, 2020.
     */
    private State getState() {

        if (body.getLinearVelocity().y > 0) { return State.JUMPING; } // Vanyr's going upwards
        else if (body.getLinearVelocity().y < 0) { return State.JUMPING; } // Vanyr's going downwards
        else if (body.getLinearVelocity().x != 0) { return State.RUNNING; } // Vanyr's not still
        else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) { return State.ATTACKING; } // Vanyr's attacking
        else if (Gdx.input.isKeyPressed(Input.Keys.R)) { return State.DYING; } // Vanyr's dying
        else { return State.STANDING; } // Vanyr's still

    }

    /**
     * Getter for the Vanyr's body.
     * @return Vanyr's body.
     * @since February 25th, 2020.
     */
    public Body getBody() { return body; }

    /**
     * Vanyr's physical definition.
     * @since January 24th, 2020.
     */
    private void defineVanyr() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(500, 24);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(16, 24);

        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);

    }

}
