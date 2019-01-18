package com.mygdx.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphics.RenderSystem;

public abstract class Map {

    private Texture background;

    public Map() {
    }

    public abstract void update();
    public abstract void render(com.mygdx.game.graphics.RenderSystem rs);

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }
}
