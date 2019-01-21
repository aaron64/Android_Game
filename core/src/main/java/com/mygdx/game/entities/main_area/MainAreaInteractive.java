package com.mygdx.game.entities.main_area;


import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.Vector2f;

public abstract class MainAreaInteractive extends MainAreaEntity {

    protected int triggerDistance;
    public MainAreaInteractive(SceneMainArea scene, Vector2f pos, String name) {
        super(scene, pos, name);
        triggerDistance = 200;
    }

    protected boolean checkDistance() {
        Vector2f playerPos = scene.getPlayer().getPos();
        float dx = playerPos.x - getPos().x;
        float dy = playerPos.y - getPos().y;

        return Math.sqrt(dx * dx + dy * dy) < triggerDistance;
    }
}
