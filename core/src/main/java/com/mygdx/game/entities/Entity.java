package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public abstract class Entity {
    private Vector2f pos;
    private Vector2i size;
    private String name;
    private Texture image;

    public Entity(Vector2f pos, String name) {
        this.pos = pos;
        this.name = name;
        this.image = null;

        initializeImage();
    }

    public Entity(Vector2f pos, String folder, String name) {
        this.pos = pos;
        this.name = name;

        try {
            image = Image.getImage("entities/" + folder + "/" + name);
            this.size = new Vector2i(image.getWidth(), image.getHeight());
        } catch (Exception e) {
            initializeImage();
        }
    }

    public abstract void update();

    public void render(RenderSystem rs, Vector2f pos) {
        rs.draw(image, pos);
    }

    public void render(RenderSystem rs) {
        rs.draw(this);
    }

    public void initializeImage() {}

    public void resetSize() {
        this.size = new Vector2i(image.getWidth(), image.getHeight());
    }

    public Vector2f getPos() {
        return pos;
    }

    public Vector2f getCenterPos() {
        return new Vector2f(pos.x + size.w()/2, pos.y + size.h()/2);
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
    }

    public void scaleWidth(int w) {
        this.size.y *= w / this.size.w();
        this.size.x = w;
    }

    public Rectangle getRect() {
        return new Rectangle(pos.x, pos.y, size.w(), size.h());
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

    public void move(Vector2i d) {
        pos.x += d.x;
        pos.y += d.y;
    }

    public void move(Vector2f d) {
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
