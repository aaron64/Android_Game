package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.util.RenderSystem;

public abstract class Entity {
    private Vector2 pos;
    private String name;
    private Texture image;

    public Entity(Vector2 pos, String name) {
        this.pos = pos;
        this.name = name;
        this.image = null;
    }

    public Entity(Vector2 pos, String folder, String name) {
        this.pos = pos;
        this.name = name;
        image = new Texture(folder + "/" + name + ".png");
    }

    public abstract void update();

    public void render(RenderSystem rs, Vector2 pos) {
        rs.draw(image, pos);
    }

    public void render(RenderSystem rs) {
        render(rs, getPos());
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }
}
