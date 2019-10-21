package com.mygdx.game.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

import java.util.ArrayList;

public class LightEngine {

    private Vector2f pos;
    private Vector2i size;

    private Color mainColor;

    private Texture shadow;

    private ArrayList<Light> lights;

    public LightEngine() {
        shadow = Image.SHADOW;
        lights = new ArrayList<Light>();

        pos = new Vector2f(0, 0);
        size = Window.getSize();

        mainColor = new Color(0.3f, 0.3f, 0.3f, 1f);
    }

    public void render(RenderSystem rs) {

        rs.setLightMode();
        rs.setColor(mainColor);
        rs.drawStatic(shadow, pos, size);

        for(Light light : lights) {
            light.render(rs);
        }

        rs.resetColor();
        rs.setNormalMode();
    }

    public void addLight(Light light) {
        lights.add(light);
    }
}
