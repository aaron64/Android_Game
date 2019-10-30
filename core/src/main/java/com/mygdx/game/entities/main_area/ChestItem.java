package com.mygdx.game.entities.main_area;

import com.mygdx.game.Game;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.Vec2f;

public class ChestItem extends MainAreaEntity {

    protected Vec2f velocity;
    protected float yTrigger;
    protected boolean grounded;

    public ChestItem(SceneMainArea scene, Vec2f pos, String name) {
        super(scene, pos, name);

        yTrigger = pos.y - 20;
        velocity = new Vec2f((float)(Math.random() * 10 - 5), 7);

        grounded = false;
    }

    @Override
    public void update() {
        if(getPos().y > yTrigger) {
            move(velocity);
            velocity.y += Game.getGravity();
        } else {
            grounded = true;
        }
    }
}
