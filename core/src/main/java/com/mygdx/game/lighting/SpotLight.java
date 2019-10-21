package com.mygdx.game.lighting;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class SpotLight extends Light {

    private Texture texture;
    public SpotLight(Vector2f pos, Vector2i size) {
        super(pos, size);
        texture = Image.getImage("lighting/SPOT_LIGHT");
    }

    public void render(RenderSystem rs) {
        rs.draw(texture, Vector2f.subtractVectors(pos, Vector2f.divideVector(size, 2)), size);
    }
}
