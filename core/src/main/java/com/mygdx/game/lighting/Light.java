package com.mygdx.game.lighting;

import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public abstract class Light {

    protected Vector2f pos;
    protected Vector2i size;

    public Light(Vector2f pos, Vector2i size) {
        this.pos = pos;
        this.size = size;
    }

    public abstract void render(RenderSystem rs);
}
