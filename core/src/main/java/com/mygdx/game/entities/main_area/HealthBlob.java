package com.mygdx.game.entities.main_area;

import com.mygdx.game.Game;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.Vector2f;

public class HealthBlob extends ChestItem {
    
    public HealthBlob(SceneMainArea scene, Vector2f pos) {
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
