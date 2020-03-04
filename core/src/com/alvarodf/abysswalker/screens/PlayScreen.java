package com.alvarodf.abysswalker.screens;

import com.alvarodf.abysswalker.AbysswalkerGame;
import com.alvarodf.abysswalker.db.DataBase;
import com.alvarodf.abysswalker.scenes.Hud;
import com.alvarodf.abysswalker.sprites.Dragon;
import com.alvarodf.abysswalker.sprites.Vanyr;
import com.alvarodf.abysswalker.tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

/**
 * Represents the game's screen which displays the game and all the visual elements. Implements the 'Screen' interface (LibGDX).
 * @since January 21st, 2020.
 * @author Alvaro de Francisco
 */
public final class PlayScreen implements Screen {

    private AbysswalkerGame game; // The game itself
    private TextureAtlas vanyrAtlas; // Vanyr's TextureAtlas
    private TextureAtlas dragonAtlas; // Enemy - Dragon's TextureAtlas

    private OrthographicCamera camera; // The orthographic camera which follows Vanyr
    private Viewport gamePort; // The game's viewport
    private Hud hud; // The game's HUD
    private Vanyr vanyr; // The player's character
    private Dragon dragon; // The enemy Dragon
    ArrayList<Dragon> dragons;

    private TmxMapLoader mapLoader; // The TiledMap Loader (.tmx)
    private TiledMap map; // The TiledMap itself
    private OrthogonalTiledMapRenderer mapRenderer; // The TiledMap rendered in an orthogonal basis

    private Music music; // Game's main music

    private World world; // World's physics
    // private Box2DDebugRenderer debugRenderer; // Debugging tool

    private DataBase db; // Game's DataBase Interface

    /**
     * PlayScreen's Constructor.
     * @param game The game. You can use 'this' when invoking this at 'AbysswalkerGame' class.
     * @since January 21st, 2020.
     */
    public PlayScreen(AbysswalkerGame game) {

        this.game = game;

        vanyrAtlas = new TextureAtlas("sprites/vanyr.pack");
        dragonAtlas = new TextureAtlas("sprites/dragon.pack");
        camera = new OrthographicCamera();
        gamePort = new FitViewport(AbysswalkerGame.VIEWPORT_WIDTH, AbysswalkerGame.VIEWPORT_HEIGHT, camera);
        hud = new Hud(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/map_forest.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -18000), true);
        // debugRenderer = new Box2DDebugRenderer();

        vanyr = new Vanyr(this);
        dragon = new Dragon(this);
        dragons = new ArrayList<Dragon>();
        dragons.add(dragon);

        music = AbysswalkerGame.manager.get("audio/main_theme.mp3", Music.class);
        music.setLooping(true);
        music.play();

        new B2WorldCreator(this);

        world.setContactListener(new ContactListener() {

            /**
             * Mandatory function which detects when two fixtures has made contact.
             * @param contact The contact between the two fixtures.
             * @since February 25th, 2020.
             */
            @Override
            public void beginContact(Contact contact) {

                ArrayList<Dragon> dragonsToRemove = new ArrayList<Dragon>();

                if (contact.getFixtureA().getBody() == vanyr.getBody() && contact.getFixtureB().getBody() == dragon.getBody()) { // If Vanyr touches the Dragon

                    vanyr.getBody().applyForceToCenter(vanyr.getX() - 20, vanyr.getX() + 20, true);
                    dragon.body.setLinearVelocity(1000, 300);
                    dragon.dragonHp -= 10;
                    dragonsToRemove.add(dragon);

                    Hud.addEXP(10);

                   // db.saveInfo(Hud.hp, Hud.damage, Hud.armor, Hud.exp, Hud.level);

                    // if (dragon.dragonHp == 0) {  }

                }

                dragons.removeAll(dragonsToRemove);



            }

            /**
             * Mandatory function not used.
             * @param contact The contact between the two fixtures.
             * @since February 25th, 2020.
             */
            @Override
            public void endContact(Contact contact) { }

            /**
             * Mandatory function not used.
             * @param contact The contact between the two fixtures.
             * @param oldManifold Not used.
             * @since February 25th, 2020.
             */
            @Override
            public void preSolve(Contact contact, Manifold oldManifold) { }

            /**
             * Mandatory function not used.
             * @param contact The contact between the two fixtures.
             * @param impulse Not used.
             * @since February 25th, 2020.
             */
            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) { }

        });

    }

    /**
     * Mandatory function from LibGDX when showing elements. Not used.
     * @since January 21st, 2020.
     */
    @Override
    public void show() { }

    /**
     * Processes the user's input in order to move Vanyr in the game.
     * @param dt Delta Time.
     * @since January 21st, 2020.
     */
    private void handleInput(float dt) {

        if (isLeft()) { vanyr.body.applyLinearImpulse(new Vector2(-100, 0), vanyr.body.getWorldCenter(), true); } // Checks left movement
        if (isRight()) { vanyr.body.applyLinearImpulse(new Vector2(100, 0), vanyr.body.getWorldCenter(), true); } // Checks right movement
        if (isUp()) { vanyr.body.applyForceToCenter(0, 100000, true); } // Checks up movement
        if (isAttack()) { vanyr.currentState = Vanyr.State.ATTACKING; } // Checks attack movement
        if (Hud.hp == 0) { vanyr.currentState = Vanyr.State.DYING; } // Checks dying moment

    }

    /**
     * This function contains statements which must be updated each delta time.
     * @param dt Delta Time.
     * @since January 21st, 2020.
     */
    private void update(float dt) {

        handleInput(dt);
        world.step(1 / 60f, 6, 2);
        vanyr.update(dt);
        dragon.update(dt);
        hud.update(dt);
        camera.position.x = vanyr.body.getPosition().x;
        camera.update();
        mapRenderer.setView(camera);

    }

    /**
     * Mandatory function from LibGDX when rendering elements. All the elements from the 'update' function are here as well.
     * @param delta Delta Time.
     * @since January 21st, 2020.
     */
    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();
        // debugRenderer.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        vanyr.draw(game.batch);
        for (Dragon dragon : dragons) {
            dragon.draw(game.batch);
        }
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    /**
     * Mandatory function from LibGDX when resizing the game's screen.
     * @param width Width in pixels
     * @param height Height in pixels
     * @since January 21st, 2020.
     */
    @Override
    public void resize(int width, int height) { gamePort.update(width, height); }

    /**
     * Mandatory function from LibGDX when pausing the game. Not used.
     * @since January 21st, 2020.
     */
    @Override
    public void pause() { }

    /**
     * Mandatory function from LibGDX when resuming the game. Not used.
     * @since January 21st, 2020.
     */
    @Override
    public void resume() { }

    /**
     * Mandatory function from LibGDX when hiding the game. Not used.
     * @since January 21st, 2020.
     */
    @Override
    public void hide() { }

    /**
     * Mandatory function from LibGDX when for disposing game elements.
     * @since January 21st, 2020.
     */
    @Override
    public void dispose() {

        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        // debugRenderer.dispose();
        hud.dispose();

    }

    /**
     * Getter for Vanyr's TextureAtlas.
     * @return Vanyr's TextureAtlas.
     * @since February 8th, 2020.
     */
    public TextureAtlas getVanyrAtlas() { return vanyrAtlas; }

    /**
     * Getter for Dragon's TextureAtlas.
     * @return Dragon's TextureAtlas.
     * @since February 25th, 2020.
     */
    public TextureAtlas getDragonAtlas() { return dragonAtlas; }

    /**
     * Getter for the Game's map.
     * @return Game's map.
     * @since February 25th, 2020.
     */
    public TiledMap getMap() { return map; }

    /**
     * Getter for the Game's World.
     * @return Game's world.
     * @since February 25th, 2020.
     */
    public World getWorld() { return world; }

    /**
     * Moves Vanyr to the right, whether using keyboard or touchscreen.
     * @return Vanyr's right movement.
     * @since February 27th, 2020.
     */
    private boolean isRight() { return Gdx.input.isKeyPressed(Input.Keys.RIGHT) || (Gdx.input.isTouched() && Gdx.input.getX() > (AbysswalkerGame.VIEWPORT_WIDTH / 3) * 2); }

    /**
     * Moves Vanyr to the left, whether using keyboard or touchscreen.
     * @return Vanyr's left movement.
     * @since February 27th, 2020.
     */
    private boolean isLeft() { return Gdx.input.isKeyPressed(Input.Keys.LEFT) || (Gdx.input.isTouched() && Gdx.input.getX() < AbysswalkerGame.VIEWPORT_WIDTH / 3); }

    /**
     * Moves Vanyr upwards, whether using keyboard or touchscreen.
     * @return Vanyr's upwards movement.
     * @since February 27th, 2020.
     */
    private boolean isUp() { return Gdx.input.isKeyPressed(Input.Keys.UP) || (Gdx.input.isTouched() && Gdx.input.getY() < AbysswalkerGame.VIEWPORT_HEIGHT); }

    /**
     * Vanyr's attack movement, whether using keyboard or touchscreen.
     * @return Vanyr's attack movement
     * @since February 27th, 2020.
     */
    private boolean isAttack() {

        return Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) || ((Gdx.input.justTouched() && Gdx.input.getX() >= AbysswalkerGame.VIEWPORT_WIDTH / 3) &&
                (Gdx.input.justTouched() && Gdx.input.getX() >= (AbysswalkerGame.VIEWPORT_WIDTH / 3)  * 2));

    }

}
