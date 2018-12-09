package com.mygdx.game.GUI;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.RenderSystem;

public abstract class GUIComponent {

    private String name;
    public GUIComponent(String name) {
        this.name = name;
    }

    public abstract void update(GUI gui, Scene scene);

    public abstract void render(GUI gui, RenderSystem rs);
}
