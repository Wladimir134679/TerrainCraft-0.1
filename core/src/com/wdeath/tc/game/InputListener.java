package com.wdeath.tc.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputListener implements InputProcessor {

    private GameScreen gameScreen;

    public InputListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT)
            gameScreen.getCursorWorld().isLeft = true;
        if(button == Input.Buttons.RIGHT)
            gameScreen.getCursorWorld().isRight = true;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
