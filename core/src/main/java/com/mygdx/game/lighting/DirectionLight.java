package com.mygdx.game.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vec2i;

public class DirectionLight extends Light {

    private Texture texture;
    private float angle;

    public DirectionLight(Entity parent, int size, Color color, float angle) {
        super(parent, new Vec2i(size, size), color);
        texture = Image.getImage("lighting/DIRECTION_LIGHT");

        this.angle = angle;
    }

    public DirectionLight(Entity parent, int size, float angle) {
        super(parent, new Vec2i(size, size));
        texture = Image.getImage("lighting/DIRECTION_LIGHT");

        this.angle = angle;
    }

    public void render(RenderSystem rs) {
        rs.getBatch().draw(texture,parent.getPos().x + parent.getSize().w()/2 - size.w()/2, parent.getPos().y + parent.getSize().h()/2 - size.h()/2, size.w() / 2, size.h() / 2, size.w(), size.h(), 1, 1, angle, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
