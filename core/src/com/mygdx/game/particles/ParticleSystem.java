package com.mygdx.game.particles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Window;

import java.util.ArrayList;

public class ParticleSystem {

    private ArrayList<Vector2> posList;
    private ArrayList<Vector2> velocityList;
    private ArrayList<Vector2> sizeList;
    private int particles;
    private Texture texture;
    public ParticleSystem() {

    }

    public void update(Scene scene) {
        for(int i = 0; i < particles; i++) {
            Vector2 pos = posList.get(i);
            Vector2 vel = velocityList.get(i);
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
