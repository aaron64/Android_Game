package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.main_area.SceneMainArea;

public class MainAreaInteractive extends MainAreaEntity {

    protected int triggerDistance;
    public MainAreaInteractive(SceneMainArea scene, Vector2 pos, String name) {
        super(scene, pos, name);
        triggerDistance = 300;
    }

    protected boolean checkDistance() {
        Vector2 playerPos = scene.getPlayer().getPos();
        float dx = playerPos.x - getPos().x;
        float dy = playerPos.y - getPos().y;

        return Math.sqrt(dx * dx + dy * dy) < triggerDistance;
    }
}
