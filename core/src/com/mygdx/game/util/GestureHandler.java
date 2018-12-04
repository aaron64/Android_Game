package com.mygdx.game.util;

import com.badlogic.gdx.math.Vector2;

public interface GestureHandler {
    void touchDown(float x, float y, int pointer, int button);
    void fling(float vx, float vy, int button);
    void zoom(float initialDistance, float distance);
    void hold(float x, float y);
}
