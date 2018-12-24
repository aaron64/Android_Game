package com.mygdx.game.GUI;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.RenderSystem;

public abstract class GUIComponent {

    protected GUI gui;
    private String name;

    public GUIComponent(GUI gui, String name) {
        this.name = name;
        this.gui = gui;
    }

    public abstract void update(Scene scene);

    public abstract void render(RenderSystem rs);
}
