package com.mygdx.game.GUI;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.graphics.RenderSystem;

public abstract class GUIComponent {

    protected GUI gui;
    private String name;
    private boolean visible;

    public GUIComponent(GUI gui, String name) {
        this.name = name;
        this.gui = gui;

        visible = true;
    }

    public abstract void update(Scene scene);

    public abstract void render(com.mygdx.game.graphics.RenderSystem rs);

    public void tap(float x, float y) {

    }

    public void off() {
        visible = false;
    }

    public void on() {
        visible = true;
    }

    public boolean isOn() {
        return visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
