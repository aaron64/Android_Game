package com.mygdx.game.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2i;

public class SpotLight extends Light {

    private Texture texture;
    public SpotLight(Entity parent, int size, Color color) {
        super(parent, new Vector2i(size, size), color);
        texture = Image.getImage("lighting/SPOT_LIGHT");
    }

    public SpotLight(Entity parent, int size) {
        super(parent, new Vector2i(size, size));
        texture = Image.getImage("lighting/SPOT_LIGHT");
    }

    public void render(RenderSystem rs) {
        rs.getBatch().draw(texture, parent.getPos().x + parent.getSize().w()/2 - size.w()/2, parent.getPos().y + parent.getSize().h()/2 - size.h()/2, size.w(), size.h());
    }
}
