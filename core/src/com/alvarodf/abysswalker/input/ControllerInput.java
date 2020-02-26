package com.alvarodf.abysswalker.input;

import com.alvarodf.abysswalker.sprites.Vanyr;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the inputs for interacting the user with Vanyr's actions, such as movements.
 * Implements the InputProcessor interface (LibGDX).
 * Currently there are problems using this with the touch functions for the Android module.
 * I need help for fixing this.
 * @since February 26th, 2020.
 * @author Alvaro de Francisco
 */
public class ControllerInput implements InputProcessor {

    private Vanyr vanyr; // The user's main character and target of this class

    /**
     * Constructor binding this class to a Vanyr variable.
     * @param vanyr The user's main character.
     * @since February 26th, 2020.
     */
    public ControllerInput(Vanyr vanyr) { this.vanyr = vanyr; }

    /**
     * Function which detects when a key has been pressed down.
     * @param keycode The key pressed.
     * @return Whether the input was processed.
     * @since February 26th, 2020.
     */
    @Override
    public boolean keyDown(int keycode) { return false; }

    /**
     * Function which detects when a key isn't pressed down anymore.
     * @param keycode The key pressed.
     * @return Whether the input was processed.
     * @since February 26th, 2020.
     */
    @Override
    public boolean keyUp(int keycode) { return false; }

    /**
     * Function which detects what character has been typed.
     * @param character The character typed.
     * @return Whether the input was processed.
     * @since February 26th, 2020.
     */
    @Override
    public boolean keyTyped(char character) { return false; }

    /**
     * Function which detects when the screen has been touched.
     * @param screenX The x coordinate, origin is in the upper left corner.
     * @param screenY The y coordinate, origin is in the upper left corner.
     * @param pointer The pointer for the event.
     * @param button The button.
     * @return Whether the input was processed.
     * @since February 26th, 2020.
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
     * Function which detects when the finger which touched the screen has lift from it.
     * @param screenX The x coordinate, origin is in the upper left corner.
     * @param screenY The y coordinate, origin is in the upper left corner.
     * @param pointer The pointer for the event.
     * @param button The button.
     * @return Whether the input was processed.
     * @since February 26th, 2020.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

    /**
     * Called when a finger or the mouse was dragged.
     * @param screenX The x coordinate, origin is in the upper left corner.
     * @param screenY The y coordinate, origin is in the upper left corner.
     * @param pointer The pointer for the event.
     * @return Whether the input was processed.
     * @since February 26th, 2020.
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

    /**
     * Called when the mouse was moved without any buttons being pressed.
     * @param screenX The x coordinate, origin is in the upper left corner.
     * @param screenY The y coordinate, origin is in the upper left corner.
     * @return Whether the input was processed.
     * @since February 26th, 2020.
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) { return false; }

    /**
     * Called when the mouse wheel was scrolled.
     * @param amount The scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return Whether the input was processed.
     * @since February 26th, 2020.
     */
    @Override
    public boolean scrolled(int amount) { return false; }

}
