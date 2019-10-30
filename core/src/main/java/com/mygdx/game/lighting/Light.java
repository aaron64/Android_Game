package com.mygdx.game.lighting;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vec2i;

public abstract class Light {

    protected Entity parent;
    protected Vec2i size;

    private Color color;

    public Light(Entity parent, Vec2i size, Color color) {
        this.parent = parent;
        this.size = size;

        this.color = color;
    }

    public Light(Entity parent, Vec2i size) {
        this(parent, size, Color.WHITE);
    }

    public abstract void render(RenderSystem rs);

    public Color getColor() {
        return color;
    }
}
