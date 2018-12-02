package com.mygdx.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.util.RenderSystem;

public abstract class Map {

    private Texture background;

    public Map(String bgPath) {
        background = new Texture("backgrounds/" + bgPath + ".png");
    }

    public abstract void update();
    public abstract void render(RenderSystem rs);

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }
}
