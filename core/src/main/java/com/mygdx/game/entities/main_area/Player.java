package com.mygdx.game.entities.main_area;


import com.mygdx.game.entities.particles.ParticleFootSteps;
import com.mygdx.game.graphics.CharacterSprite;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

public class Player extends MainAreaEntity {

    float maxVelocity;
    private int maxHealth, health;
    private CharacterSprite sprite;

    private ParticleFootSteps particleFootSteps;

    public Player(SceneMainArea scene, Vec2f pos, String name) {
        super(scene, pos, name);

        sprite = new CharacterSprite(getImage(), new Vec2i(36, 78), 16, 6);
        setSize(sprite.getSize());

        maxVelocity = 5;

        maxHealth = 100;
        health = maxHealth;

        particleFootSteps = new ParticleFootSteps(getPos(), "grass", 0, 0, 20, 10, 8, 4, -1, 0, 1, 1, 20, 10, this, scene);
    }

    @Override
    public void update() {
        particleFootSteps.update();
    }

    @Override
    public void render(RenderSystem rs) {
        particleFootSteps.render(rs);
        sprite.render(rs, getPos());
    }

    public void move(Vec2f touchVector, SceneMainArea scene) {
        Vec2f touchDirection = MathUtil.getUnitVector(touchVector);

        float velocityMagnitude = Math.min(MathUtil.getDistance(touchVector)/100f, maxVelocity);
        Vec2f velocity = Vec2f.multiplyVector(touchDirection, velocityMagnitude);

        sprite.update(velocity);

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

        if(Math.random() < 0.2) {
            particleFootSteps.setxVelocity(-velocity.x/10);
            particleFootSteps.setyVelocity(-velocity.y/10);
            particleFootSteps.addParticles(2);
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

        if(Math.random() < 0.4) {
            particleFootSteps.setxVelocity(-velocity.x/5);
            particleFootSteps.setyVelocity(-velocity.y/5);
            particleFootSteps.addParticles(2);
        }
    }

    public void resetSprite() {
        sprite.reset();
    }

    public int getHealth() {
        return health;
    }

    public boolean atMaxHealth() {
        return health >= maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void heal(int amount) {
        health += amount;
        if(health > maxHealth) {
            health = maxHealth;
        }
    }
}
