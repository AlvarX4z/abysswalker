package com.alvarodf.abysswalker.input;

import com.alvarodf.abysswalker.sprites.Vanyr;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * @since February 26th, 2020
 */
public class ControllerInput implements InputProcessor {

    private Vanyr vanyr;

    /**
     *
     * @param vanyr
     * @since February 26th, 2020
     */
    public ControllerInput(Vanyr vanyr) { this.vanyr = vanyr; }

    /**
     *
     * @param keycode
     * @return
     * @since February 26th, 2020
     */
    @Override
    public boolean keyDown(int keycode) { return false; }

    /**
     *
     * @param keycode
     * @return
     * @since February 26th, 2020
     */
    @Override
    public boolean keyUp(int keycode) { return false; }

    /**
     *
     * @param character
     * @return
     * @since February 26th, 2020
     */
    @Override
    public boolean keyTyped(char character) { return false; }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     * @since February 26th, 2020
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (screenX > Gdx.graphics.getWidth() / 4) { vanyr.body.applyLinearImpulse(new Vector2(-100, 0), vanyr.body.getWorldCenter(), true); }
        if (screenX > ((Gdx.graphics.getWidth() / 4) * 3)) { vanyr.body.applyLinearImpulse(new Vector2(100, 0), vanyr.body.getWorldCenter(), true); }
        if (screenY > Gdx.graphics.getHeight() / 3) { vanyr.body.applyForceToCenter(0, 100000, true); }
        if (screenX > Gdx.graphics.getWidth() / 4 && screenX < ((Gdx.graphics.getWidth() / 4) * 3)) { vanyr.currentState = Vanyr.State.ATTACKING; }

        return false;

    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @param button
     * @return
     * @since February 26th, 2020
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @param pointer
     * @return
     * @since February 26th, 2020
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     *
     * @param screenX
     * @param screenY
     * @return
     * @since February 26th, 2020
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     *
     * @param amount
     * @return
     * @since February 26th, 2020
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
