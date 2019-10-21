package com.mygdx.game.particles;

import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.graphics.Image;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.graphics.Window;

public class ParticleSystem {

    private Vector2f[] posList;
    private Vector2f[] velocityList;
    private Vector2i[] sizeList;
    private int particles;
    private Texture texture;
    public ParticleSystem(String textureStr, int particles, float xv, float yv, float xvRange, float yvRange, int size, int sizeRange) {
        this.texture = Image.getImage("entities/particles/" + textureStr);
        this.particles = particles;

        posList = new Vector2f[particles];
        velocityList = new Vector2f[particles];
        sizeList = new Vector2i[particles];

        for(int i = 0; i < particles; i++) {
            float px = (float)(Math.random() * Window.getWidth());
            float py = (float)(Math.random() * Window.getHeight());

            float pxv = (float)((Math.random() * 2 * xvRange) - xvRange + xv);
            float pyv = (float)((Math.random() * 2 * yvRange) - yvRange + yv);

            int ps = (int)((Math.random() * 2 * sizeRange) - sizeRange + size);

            posList[i] = new Vector2f(px, py);
            velocityList[i] = new Vector2f(pxv, pyv);
            sizeList[i] = new Vector2i(ps, ps);
        }
    }

    public void update(Scene scene) {
        for(int i = 0; i < particles; i++) {
            Vector2f pos = posList[i];
            Vector2f vel = velocityList[i];

            pos.x += vel.x;
            pos.y += vel.y;

            if(!Window.inWindow(pos)) {
                if(pos.x > 0) {
                    pos.x -= Window.getWidth();
                } else {
                    pos.x += Window.getWidth();
                }
                if(pos.y > 0) {
                    pos.y -= Window.getHeight();
                } else {
                    pos.y += Window.getHeight();
                }
            }
        }
    }

    public void render(RenderSystem rs) {
        for(int i = 0; i < particles; i++) {
            rs.draw(texture, posList[i], sizeList[i]);
        }
    }
}
