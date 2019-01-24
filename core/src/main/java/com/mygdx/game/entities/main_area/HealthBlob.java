package com.mygdx.game.entities.main_area;

import com.mygdx.game.Game;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.Vector2f;

public class HealthBlob extends MainAreaEntity {

    private Vector2f velocity;
    private float yTrigger;
    
    public HealthBlob(SceneMainArea scene, Vector2f pos) {
        super(scene, pos, "health_blob");
        solid = false;

        yTrigger = pos.y - 20;
        velocity = new Vector2f((float)(Math.random() * 10 - 5), 7);
    }

    @Override
    public void update() {
        if(getPos().y > yTrigger) {
            move(velocity);
            velocity.y += Game.getGravity();
        } else {
            if(collidesWith(scene.getPlayer()) && !scene.getPlayer().atMaxHealth()) {
                scene.getPlayer().heal(10);
                scene.removeEntity(this);
            }
        }
    }
}
