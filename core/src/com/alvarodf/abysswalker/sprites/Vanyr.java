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
public class Vanyr extends Sprite {

    public enum State { STANDING, JUMPING, ATTACKING, RUNNING, DYING }
    public State currentState;
    public State previousState;

    private World world;
    public Body body;
    private TextureRegion vanyrStand;

    private Animation vanyrStanding;
    private Animation vanyrJumping;
    private Animation vanyrAttacking;
    private Animation vanyrRunning;
    private Animation vanyrDying;

    private float stateTimer;
    private boolean runningRight;


    /**
     * @since January 24th, 2020
     * @param world
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
        Array<TextureRegion> attackingFrames = new Array<>();
        Array<TextureRegion> runningFrames = new Array<>();
        Array<TextureRegion> dyingFrames = new Array<>();

        // STANDING
        for (int i = 0; i <= 0; i++) {

            for (int j = 0; j <= 3; j++) { standingFrames.add(new TextureRegion(getTexture(), 16 * i, 0, 48, 48)); }

        }

        vanyrStanding = new Animation(0.1f, standingFrames);
        standingFrames.clear();

        // ATTACKING
        for (int i = 1; i <= 1; i++) {

            for (int j = 0; j <= 5; j++) { attackingFrames.add(new TextureRegion(getTexture(), 16 * i, 0, 48, 48)); }

        }

        vanyrAttacking = new Animation(0.1f, attackingFrames);
        attackingFrames.clear();

        // JUMPING
        for (int i = 3; i <= 3; i++) {

            for (int j = 0; j <= 3; j++) { jumpingFrames.add(new TextureRegion(getTexture(), 16 * i, 0, 48, 48)); }

        }

        vanyrJumping = new Animation(0.1f, jumpingFrames);
        jumpingFrames.clear();

        // RUNNING
        for (int i = 4; i <= 4; i++) {

            for (int j = 0; j <= 5; j++) { runningFrames.add(new TextureRegion(getTexture(), 16 * i, 0, 48, 48)); }

        }

        vanyrRunning = new Animation(0.1f, runningFrames);
        runningFrames.clear();

        // DYING
        for (int i = 6; i <= 6; i++) {

            for (int j = 0; j <= 2; j++) { dyingFrames.add(new TextureRegion(getTexture(), 16 * i, 0, 48, 48)); }

        }

        vanyrDying = new Animation(0.1f, dyingFrames);
        dyingFrames.clear();

        vanyrStand = new TextureRegion(getTexture(), 26, 0, 48, 48);
        defineVanyr();
        setBounds(0, 0, 48, 48);
        setRegion(vanyrStand);

    }

    public void update(float dt) { setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2); }

    /**
     * @since January 24th, 2020
     */
    private void defineVanyr() {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(20, 200);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape rectangle = new PolygonShape();

        rectangle.setAsBox(16, 24);

        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);

    }

}
