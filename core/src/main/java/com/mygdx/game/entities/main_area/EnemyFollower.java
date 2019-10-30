package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.lighting.DirectionLight;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vec2f;

public class EnemyFollower extends Enemy {

    private float range;
    private float directionRange;
    private Player target;
    private float velocity;

    private float angle, angleTarget;

    DirectionLight light;

    public EnemyFollower(SceneMainArea scene, Vec2f pos, String name) {
        super(scene, pos, name);

        range = 100;
        directionRange = 220;

        velocity = 2;

        angle = (float)(Math.random() * 360);
        angleTarget = angle;

        light = new DirectionLight(this, 512, new Color(0.4f, 0.2f, 0.2f, 1), angle);
        scene.getLightEngine().addLight(light);
    }

    @Override
    public void update() {
        if(target == null) {
            for (Entity e : scene.getEntities()) {
                if(e instanceof Player) {
                    if (MathUtil.getDistance(getPos(), e.getPos()) < range) {
                        target = (Player) e;
                    }
                }
            }
        } else {
            Vec2f distVector = Vec2f.subtractVectors(target.getPos(), getPos());
            float dist = MathUtil.getDistance(distVector);

            if(dist > range)
                target = null;

            float vx = (distVector.x / dist) * velocity;
            float vy = (distVector.y / dist) * velocity;

            move(new Vec2f(vx, vy));
        }
    }

    @Override
    public void move(Vec2f velocity) {
        moveX(velocity.x);

        MainAreaEntity collision = scene.getSolidEntityCollision(this);
        if(collision != null) {
            moveX(-velocity.x);
            collision.touch(this);
        } else if(!scene.getGrid().isInMap(this)){
            moveX(-velocity.x);
        }

        moveY(velocity.y);
        collision = scene.getSolidEntityCollision(this);
        if(collision != null) {
            moveY(-velocity.y);
            collision.touch(this);
        } else if(!scene.getGrid().isInMap(this)) {
            moveY(-velocity.y);
        }

        angle = MathUtil.getAngle(velocity);
        light.setAngle(angle);
    }
}
