package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.MathUtil;

public class Player extends MainAreaEntity {

    float maxVelocity;
    private int health;

    public Player(Vector2 pos, String name) {
        super(pos, name);
        maxVelocity = 7;

        health = 100;
    }

    @Override
    public void update(Scene scene) {

    }

    public void move(Vector2 touchVector, SceneMainArea scene) {
        touchVector.y *= -1;
        Vector2 touchDirection = MathUtil.getUnitVector(touchVector);

        float velocity = Math.min(MathUtil.getDistance(touchVector)/100f, maxVelocity);
        Vector2 offset = MathUtil.multiplyVec(touchDirection, velocity);

        moveX(offset.x);

        MainAreaEntity collision = scene.getEntityCollision(this);
        if(collision != null) {
            moveX(-offset.x);
            collision.touch(this, scene);
        } else if(!scene.getGrid().isInMap(this)){
            moveX(-offset.x);
        }

        moveY(offset.y);
        collision = ((SceneMainArea) scene).getEntityCollision(this);
        if(collision != null) {
            moveY(-offset.y);
            collision.touch(this, scene);
        } else if(!scene.getGrid().isInMap(this)) {
            moveY(-offset.y);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
