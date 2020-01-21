package com.alvarodf.abysswalker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


/**
 * @author Alvaro de Francisco
 * @since January 20th, 2020
 */
public class AbysswalkerGame extends ApplicationAdapter {

    private SpriteBatch batch;

    private Texture vanyrTexture;
    private float vanyrX;
    private float vanyrY;
    private Rectangle vanyrRectangle;

    private Texture enemyTexture;
    private float enemyX;
    private float enemyY;
    private Rectangle enemyRectangle;

    private Texture mapTexture;
    private Texture winMessageTexture;
    private boolean win;

    /**
     * @author Alvaro de Francisco
     * @since January 20th, 2020
     */
    @Override
    public void create() {

        batch = new SpriteBatch();

        vanyrTexture = new Texture(Gdx.files.internal("android/assets/prueba.png"));
        vanyrX = 20;
        vanyrY = 20;
        vanyrRectangle = new Rectangle((int)vanyrX, (int)vanyrY, vanyrTexture.getWidth(), vanyrTexture.getHeight());

        enemyTexture = new Texture(Gdx.files.internal("android/assets/prueba.png"));
        enemyX = 100;
        enemyY = 100;
        enemyRectangle = new Rectangle((int)enemyX, (int)enemyY, enemyTexture.getWidth(), enemyTexture.getHeight());

        mapTexture = new Texture(Gdx.files.internal("android/assets/badlogic.jpg"));


        win = false;

    }

    /**
     * @author Alvaro de Francisco
     * @since January 20th, 2020
     */
    @Override
    public void render() {

        // Check user input
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { vanyrX--; }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) { vanyrX++; }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) { vanyrY++; }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) { vanyrY--; }

        Gdx.app.debug("Movimiento", Gdx.input.isKeyPressed(Input.Keys.UP) + "");

        // update turtle rectangle location
        vanyrRectangle.setPosition(vanyrX, vanyrY);

        // check win condition: turtle must be overlapping starfish
        if (vanyrRectangle.overlaps(enemyRectangle)) { win = true; }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(mapTexture, 0, 0);
        if (!win) {
            batch.draw(vanyrTexture, 100, 100);
            batch.draw(enemyTexture, 200, 200);
        } else {
            Gdx.app.log("Win", win + "");
        }

        batch.end();

    }

    /**
     * @author Alvaro de Francisco
     * @since January 20th, 2020
     */
    @Override
    public void dispose() {

        batch.dispose();
        vanyrTexture.dispose();
        enemyTexture.dispose();

    }

}
