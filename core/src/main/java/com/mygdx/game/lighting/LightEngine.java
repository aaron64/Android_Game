package com.mygdx.game.lighting;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

import java.util.ArrayList;

public class LightEngine {

    private Vec2f pos;
    private Vec2i size;

    private Color mainColor;

    private Texture shadow;

    private ArrayList<Light> lights;

    private FrameBuffer fbo;
    private TextureRegion fbo_tr;

    public LightEngine() {
        shadow = Image.SHADOW;
        lights = new ArrayList<Light>();

        pos = new Vec2f();
        size = Window.getSize();

        mainColor = new Color(0.25f, 0.25f, 0.25f, 1f);

        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, Window.getWidth(), Window.getHeight(), false);
        fbo_tr = new TextureRegion(fbo.getColorBufferTexture());
        fbo_tr.flip(false, true);
    }

    public void render(RenderSystem rs) {

        fbo.begin();
        rs.begin();

        rs.setColor(mainColor);
        rs.drawStatic(shadow, pos, size);
        rs.resetColor();

        for(Light light : lights) {
            rs.setColor(light.getColor());
            light.render(rs);
        }
        rs.resetColor();

        rs.end();
        fbo.end();
    }

    public void draw(RenderSystem rs) {
        rs.setShader(RenderSystem.lightingShader);
        rs.drawFBO(fbo_tr);
        rs.setShader(null);
    }

    public TextureRegion getImage() {
        return fbo_tr;
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    public void removeLight(Light light){
        lights.remove(light);
    }
}
