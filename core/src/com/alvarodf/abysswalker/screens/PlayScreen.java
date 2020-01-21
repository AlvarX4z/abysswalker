package com.alvarodf.abysswalker.screens;

import com.alvarodf.abysswalker.AbysswalkerGame;
import com.alvarodf.abysswalker.scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Represents the game's screen which displays the game and all the visual elements. Implements the 'Screen' interface (LibGDX).
 * @author Alvaro de Francisco
 * @since January 21st, 2020
 */
public class PlayScreen implements Screen {

    private AbysswalkerGame game; // The game
    private OrthographicCamera camera; // The orthographic camera
    private Viewport gamePort; // The game's viewport
    private Hud hud; // The game's HUD

    private TmxMapLoader mapLoader; // The TiledMap Loader (.tmx)
    private TiledMap map; // The TiledMap itself
    private OrthogonalTiledMapRenderer mapRenderer; // The TiledMap rendered in an orthogonal basis

    /**
     * PlayScreen's Constructor.
     * @param game The game. You can use 'this' when invoking this at 'AbysswalkerGame' class.
     * @since January 21st, 2020
     */
    public PlayScreen(AbysswalkerGame game) {

        this.game = game;

        camera = new OrthographicCamera();
        gamePort = new FitViewport(AbysswalkerGame.VIEWPORT_WIDTH, AbysswalkerGame.VIEWPORT_HEIGHT, camera); // Probar con Stretch, Screen, Scaling, Fit VIEWPORT
        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("android/assets/prueba1.tmx"); // Stating map's path

        Gdx.app.log("Mapa Cargado", (map != null) + ""); // Debugging purposes

        mapRenderer = new OrthogonalTiledMapRenderer(map);

        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    /**
     * Mandatory function from LibGDX when showing elements.
     * @since January 21st, 2020
     */
    @Override
    public void show() { }

    /**
     * Mandatory function from LibGDX when rendering elements.
     * @param delta Delta Time
     * @since January 21st, 2020
     */
    @Override
    public void render(float delta) {

        update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
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

    /**
     *
     * @param dt Delta Time
     * @since January 21st, 2020
     */
    public void handleInput(float dt) {

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.Y)) {

            Gdx.app.log("Tecla Y", Gdx.input.isKeyJustPressed(Input.Keys.Y) + "");
            camera.position.x += 100 * dt;

        }

    }

    /**
     *
     * @param dt Delta Time
     * @since January 21st, 2020
     */
    public void update(float dt) {

        handleInput(dt);
        camera.update();
        mapRenderer.setView(camera);

    }

}
