package com.alvarodf.abysswalker.screens;

import com.alvarodf.abysswalker.AbysswalkerGame;
import com.alvarodf.abysswalker.scenes.Hud;
import com.alvarodf.abysswalker.sprites.Vanyr;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents the game's screen which displays the game and all the visual elements. Implements the 'Screen' interface (LibGDX).
 * @author Alvaro de Francisco
 * @since January 21st, 2020
 */
public class PlayScreen implements Screen {

    private AbysswalkerGame game; // The game itself
    private OrthographicCamera camera; // The orthographic camera which follows Vanyr
    private Viewport gamePort; // The game's viewport
    private Hud hud; // The game's HUD
    private Vanyr vanyr; // The player's character

    private TmxMapLoader mapLoader; // The TiledMap Loader (.tmx)
    private TiledMap map; // The TiledMap itself
    private OrthogonalTiledMapRenderer mapRenderer; // The TiledMap rendered in an orthogonal basis

    // ----------------------- BoX2D Variables -----------------------

    private World world;
    private Box2DDebugRenderer debugRenderer;

    /**
     * PlayScreen's Constructor.
     * @param game The game. You can use 'this' when invoking this at 'AbysswalkerGame' class.
     * @since January 21st, 2020
     */
    public PlayScreen(AbysswalkerGame game) {

        this.game = game;

        camera = new OrthographicCamera();
        gamePort = new FitViewport(AbysswalkerGame.VIEWPORT_WIDTH, AbysswalkerGame.VIEWPORT_HEIGHT, camera); //
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("android/assets/maps/map_forest.tmx"); // Stating map's path
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0); // Sets the camera at the start of the level

        world = new World(new Vector2(0, -98), true); // Physics for gravity and sleeping objects

        debugRenderer = new Box2DDebugRenderer();

        vanyr = new Vanyr(world);

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for (MapObject object : map.getLayers().get("forest_ground").getObjects().getByType(RectangleMapObject.class)) { // Creates body for ground

            Rectangle rect = ((RectangleMapObject)object).getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(0, 0);

            body = world.createBody(bodyDef);

            shape.setAsBox(rect.getWidth(), rect.getHeight() / 10);

            fixtureDef.shape = shape;

            body.createFixture(fixtureDef);

        }

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
     * @since January 21st, 2020
     */
    private void handleInput(float dt) {

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) { vanyr.body.applyLinearImpulse(new Vector2(0, 4), vanyr.body.getWorldCenter(), true); }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && vanyr.body.getLinearVelocity().x <= 2) {

            vanyr.body.applyLinearImpulse(new Vector2(10000 * dt, 0), vanyr.body.getWorldCenter(), true);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && vanyr.body.getLinearVelocity().x >= -2) {

            vanyr.body.applyLinearImpulse(new Vector2(-10000 * dt, 0), vanyr.body.getWorldCenter(), true);

        }

    }

    /**
     *
     * @param dt Delta Time
     * @since January 21st, 2020
     */
    private void update(float dt) {

        handleInput(dt);
        world.step(1 / 60f, 6, 2);
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
    public void dispose() { }

}
