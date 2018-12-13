package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.util.RenderSystem;

public abstract class Entity {
    private Vector2 pos;
    private Vector2 size;
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
        image = new Texture("entities/" + folder + "/" + name + ".png");
        this.size = new Vector2(image.getWidth(), image.getHeight());
    }

    public abstract void update();

    public void render(RenderSystem rs, Vector2 pos) {
        rs.draw(image, pos);
    }

    public void render(RenderSystem rs) {
        rs.draw(this);
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Rectangle getRect() {
        return new Rectangle(pos.x, pos.y, size.x, size.y);
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

    public void moveX(float dx) {
        pos.x += dx;
    }

    public void moveY(float dy) {
        pos.y += dy;
    }

    public void move(Vector2 d) {
        pos.x += d.x;
        pos.y += d.y;
    }

    public void setX(float x) {
        pos.x = x;
    }

    public void setY(float y) {
        pos.y = y;
    }
}
