package com.alvarodf.abysswalker.screens;

import com.alvarodf.abysswalker.AbysswalkerGame;
import com.alvarodf.abysswalker.db.DataBase;
import com.alvarodf.abysswalker.input.ControllerInput;
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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents the game's screen which displays the game and all the visual elements. Implements the 'Screen' interface (LibGDX).
 * @author Alvaro de Francisco
 * @since January 21st, 2020
 */
public final class PlayScreen implements Screen {

    private AbysswalkerGame game; // The game itself
    private TextureAtlas vanyrAtlas; // Vanyr's TextureAtlas
    private TextureAtlas dragonAtlas; // Enemy - Dragon's TextureAtlas

    private OrthographicCamera camera; // The orthographic camera which follows Vanyr
    private Viewport gamePort; // The game's viewport
    private Hud hud; // The game's HUD
    private Vanyr vanyr; // The player's character
    private Dragon dragon;
    private ControllerInput input;

    private TmxMapLoader mapLoader; // The TiledMap Loader (.tmx)
    private TiledMap map; // The TiledMap itself
    private OrthogonalTiledMapRenderer mapRenderer; // The TiledMap rendered in an orthogonal basis

    private Music music;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private DataBase db;

    /**
     * PlayScreen's Constructor.
     * @param game The game. You can use 'this' when invoking this at 'AbysswalkerGame' class.
     * @since January 21st, 2020
     */
    public PlayScreen(AbysswalkerGame game) {

        this.game = game;

        vanyrAtlas = new TextureAtlas("sprites/vanyr.pack");
        dragonAtlas = new TextureAtlas("sprites/dragon.pack");
        camera = new OrthographicCamera();
        gamePort = new FitViewport(AbysswalkerGame.VIEWPORT_WIDTH, AbysswalkerGame.VIEWPORT_HEIGHT, camera);
        hud = new Hud(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("maps/map_forest.tmx"); // Stating map's path
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0); // Sets the camera at the start of the level

        world = new World(new Vector2(0, -18000), true); // Physics for gravity and sleeping objects
        debugRenderer = new Box2DDebugRenderer();

        vanyr = new Vanyr(this);
        dragon = new Dragon(this);
        dragon.flip(true, false);

        music = AbysswalkerGame.manager.get("audio/main_theme.mp3", Music.class);
        music.setLooping(true);
        music.play();

        input = new ControllerInput(vanyr);
        Gdx.input.setInputProcessor(input);

        new B2WorldCreator(this);

        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {

                if (contact.getFixtureA().getBody() == vanyr.getBody() && contact.getFixtureB().getBody() == dragon.getBody()) {

                    vanyr.getBody().applyForceToCenter(vanyr.getX() - 20, vanyr.getX() + 20, true);
                    dragon.body.setLinearVelocity(1000, 300);
                    dragon.dragonHp -= 10;

                    Hud.addEXP(1);

                    db.saveInfo(Hud.hp, Hud.damage, Hud.armor, Hud.exp, Hud.level);

                    // if (dragon.dragonHp == 0) {  }

                }

            }

            @Override
            public void endContact(Contact contact) { }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) { }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) { }

        });

    }

    /**
     * Mandatory function from LibGDX when showing elements.
     * @since January 21st, 2020
     */
    @Override
    public void show() { }

    /**
     *
     * @param dt Delta Time
     * @since January 21st, 2020s
     */
    private void handleInput(float dt) {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { vanyr.body.applyLinearImpulse(new Vector2(-100, 0), vanyr.body.getWorldCenter(), true); }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { vanyr.body.applyLinearImpulse(new Vector2(100, 0), vanyr.body.getWorldCenter(), true); }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) { vanyr.body.applyForceToCenter(0, 100000, true); }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) { vanyr.currentState = Vanyr.State.ATTACKING; }
        if (Hud.hp == 0) { vanyr.currentState = Vanyr.State.DYING; }

    }

    /**
     *
     * @param dt Delta Time
     * @since January 21st, 2020
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
     * Mandatory function from LibGDX when rendering elements.
     * @param delta Delta Time
     * @since January 21st, 2020
     */
    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1); // Clears the game using a black color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();
        debugRenderer.render(world, camera.combined);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        vanyr.draw(game.batch);
        dragon.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); // Sets the batch to draw what the HUD camera sees
        hud.stage.draw();

    }

    /**
     * Mandatory function from LibGDX when resizing the game's screen.
     * @param width Width in pixels
     * @param height Height in pixels
     * @since January 21st, 2020
     */
    @Override
    public void resize(int width, int height) { gamePort.update(width, height); }

    /**
     * Mandatory function from LibGDX when pausing the game.
     * @since January 21st, 2020
     */
    @Override
    public void pause() { }

    /**
     * Mandatory function from LibGDX when resuming the game.
     * @since January 21st, 2020
     */
    @Override
    public void resume() { }

    /**
     * Mandatory function from LibGDX when hiding the game.
     * @since January 21st, 2020
     */
    @Override
    public void hide() { }

    /**
     * Mandatory function from LibGDX when for disposing game elements.
     * @since January 21st, 2020
     */
    @Override
    public void dispose() {

        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        debugRenderer.dispose();
        hud.dispose();

    }

    /**
     *
     * @return
     * @since February 8th, 2020
     */
    public TextureAtlas getVanyrAtlas() { return vanyrAtlas; }

    /**
     *
     * @return
     * @since February 25th, 2020
     */
    public TextureAtlas getDragonAtlas() { return dragonAtlas; }

    /**
     *
     * @return
     * @since February 25th, 2020
     */
    public TiledMap getMap() { return map; }

    /**
     *
     * @return
     * @since February 25th, 2020
     */
    public World getWorld() { return world; }

}
