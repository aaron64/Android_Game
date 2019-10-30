package com.mygdx.game.entities.main_area;

import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.Vec2f;

public class HealthBlob extends ChestItem {
    
    public HealthBlob(SceneMainArea scene, Vec2f pos) {
        super(scene, pos, "health_blob");
        solid = false;
    }

    @Override
    public void update() {
        super.update();
        if(grounded) {
            if(collidesWith(scene.getPlayer()) && !scene.getPlayer().atMaxHealth()) {
                scene.getPlayer().heal(10);
                scene.removeEntity(this);
            }
        }
    }
}
