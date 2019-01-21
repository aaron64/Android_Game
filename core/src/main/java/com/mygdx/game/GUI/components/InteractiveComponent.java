package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.GestureHandler;
import com.mygdx.game.util.GestureUtil;
import com.mygdx.game.graphics.RenderSystem;

public class InteractiveComponent extends GUIComponent implements GestureHandler {

    private GestureUtil gestureUtil;
    public InteractiveComponent(GUI gui, String name) {
        super(gui, name);

        gestureUtil = new GestureUtil(this);
    }

    @Override
    public void update(Scene scene) {
        gestureUtil.update();
    }

    @Override
    public void render(RenderSystem rs) {

    }

    @Override
    public void touchDown(float x, float y, int pointer, int button) {

    }

    @Override
    public void fling(float vx, float vy, int button) {

    }

    @Override
    public void zoom(float initialDistance, float distance) {

    }

    @Override
    public void hold(float x, float y) {

    }

    @Override
    public void doubleTap(float x, float y) {

    }

    @Override
    public void tap(float x, float y) {

    }

    @Override
    public void stopHold(float x, float y) {

    }
}
