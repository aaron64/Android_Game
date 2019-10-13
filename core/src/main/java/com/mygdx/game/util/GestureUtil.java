package com.mygdx.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.scenes.Scene;


public class GestureUtil implements GestureDetector.GestureListener {

    private static InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private GestureDetector gestureDetector;

    private Scene scene;

    private boolean held = false;
    private float heldX, heldY;

    private int doubleTapFrameCount = 0;
    private int doubelTapThreshold = 20;

    GestureHandler handler;

    public GestureUtil(GestureHandler handler) {

        gestureDetector = new GestureDetector(this);
        inputMultiplexer.addProcessor(gestureDetector);

        this.handler = handler;

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void update() {
        if(held) {
            handler.hold(heldX, heldY);
        }

        doubleTapFrameCount++;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(isValid()) {
            y *= -1;
            y += Window.getHeight();

            handler.touchDown(x, y, pointer, button);
            held = true;
            heldX = x;
            heldY = y;
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if(isValid()) {
            y *= -1;
            y += Window.getHeight();

            handler.tap(x, y);

            held = false;

            if (doubleTapFrameCount < doubelTapThreshold)
                handler.doubleTap(x, y);

            doubleTapFrameCount = 0;
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        if(isValid()) {
            y *= -1;
            y += Window.getHeight();
        }
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if(isValid()) {
            handler.fling(velocityX, velocityY, button);
        }
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if(isValid()) {
            y *= -1;
            y += Window.getHeight();

            heldX = x;
            heldY = y;
        }
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if(isValid()) {
            handler.stopHold(x, y);
            held = false;
        }
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if(isValid()) {
            handler.zoom(initialDistance, distance);
            held = false;
        }
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        if(isValid()) {

        }
        return false;
    }

    @Override
    public void pinchStop() {
        if(isValid()) {

        }
    }

    private boolean isValid() {
        return Game.getCurrentScene() == handler;
    }

    public void reset() {
        held = false;
    }
}
