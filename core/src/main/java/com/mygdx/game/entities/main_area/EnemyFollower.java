package com.mygdx.game.entities.main_area;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vector2f;

public class EnemyFollower extends Enemy {

    private float range;
    private Player target;
    private float velocity;

    public EnemyFollower(SceneMainArea scene, Vector2f pos, String name) {
        super(scene, pos, name);

        range = 100;
        velocity = 2;
    }

    @Override
    public void update() {
        if(target == null) {
            for (Entity e : scene.getEntities()) {
                if (e instanceof Player && MathUtil.getDistance(getPos(), e.getPos()) < range) {
                    target = (Player) e;
                }
            }
        } else {
            Vector2f distVector = Vector2f.subtractVectors(target.getPos(), getPos());
            float dist = MathUtil.getDistance(distVector);

            if(dist > range)
                target = null;

            float vx = (distVector.x / dist) * velocity;
            float vy = (distVector.y / dist) * velocity;

            move(new Vector2f(vx, vy));
        }
    }

    @Override
    public void move(Vector2f velocity) {
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
    }
}
