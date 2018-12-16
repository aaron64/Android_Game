package com.mygdx.game.entities.main_area;


import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vector2f;

public class Player extends MainAreaEntity {

    float maxVelocity;
    private int maxHealth, health;

    public Player(SceneMainArea scene, Vector2f pos, String name) {
        super(scene, pos, name);
        maxVelocity = 7;

        maxHealth = 100;
        health = maxHealth;
    }

    @Override
    public void update() {

    }

    public void move(Vector2f touchVector, SceneMainArea scene) {
        touchVector.y *= -1;
        Vector2f touchDirection = MathUtil.getUnitVector(touchVector);

        float velocity = Math.min(MathUtil.getDistance(touchVector)/100f, maxVelocity);
        Vector2f offset = Vector2f.multiplyVector(touchDirection, velocity);

        moveX(offset.x);

        MainAreaEntity collision = scene.getSolidEntityCollision(this);
        if(collision != null) {
            moveX(-offset.x);
            collision.touch(this);
        } else if(!scene.getGrid().isInMap(this)){
            moveX(-offset.x);
        }

        moveY(offset.y);
        collision = ((SceneMainArea) scene).getSolidEntityCollision(this);
        if(collision != null) {
            moveY(-offset.y);
            collision.touch(this);
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

    public void heal(int amount) {
        health += amount;
        if(health > maxHealth) {
            health = maxHealth;
        }
    }
}
