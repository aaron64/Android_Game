package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class GestureUtil implements GestureDetector.GestureListener {

    private InputMultiplexer inputMultiplexer;
    private GestureDetector gestureDetector;

    private boolean held = false;
    private float heldX, heldY;

    GestureHandler handler;

    public GestureUtil(GestureHandler handler) {
        inputMultiplexer = new InputMultiplexer();
        gestureDetector = new GestureDetector(this);
        inputMultiplexer.addProcessor(gestureDetector);

        this.handler = handler;

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void update() {
        if(held) {
            handler.hold(heldX, heldY);
        }
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        handler.touchDown(x, y, pointer, button);
        held = true;
        heldX = x;
        heldY = y;
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        held = false;
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        handler.fling(velocityX, velocityY, button);
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        heldX = x;
        heldY = y;
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        held = false;
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        handler.zoom(initialDistance, distance);
        held = false;
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
