package com.mygdx.game.particles;

import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.util.Window;

import java.util.ArrayList;

public class ParticleSystem {

    private ArrayList<Vector2f> posList;
    private ArrayList<Vector2f> velocityList;
    private ArrayList<Vector2i> sizeList;
    private int particles;
    private Texture texture;
    public ParticleSystem() {

    }

    public void update(Scene scene) {
        for(int i = 0; i < particles; i++) {
            Vector2f pos = posList.get(i);
            Vector2f vel = velocityList.get(i);
            //Vector2 size = sizeList.get(i);

            pos.x += vel.x;
            pos.y += vel.y;

            if(!Window.inWindow(pos)) {
                if(vel.x > 0) {
                    pos.x -= Window.getWidth();
                } else {
                    pos.x += Window.getWidth();
                }
                if(vel.y > 0) {
                    pos.y -= Window.getHeight();
                } else {
                    pos.y += Window.getHeight();
                }
            }
        }
    }

    public void render(RenderSystem rs) {
        for(int i = 0; i < particles; i++) {
            rs.draw(texture, posList.get(i), sizeList.get(i));
        }
    }
}
