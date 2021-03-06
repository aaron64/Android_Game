package com.mygdx.game.entities.main_area;


import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.graphics.CharacterSprite;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class Player extends MainAreaEntity {

    float maxVelocity;
    private int maxHealth, health;
    private CharacterSprite sprite;

    public Player(SceneMainArea scene, Vector2f pos, String name) {
        super(scene, pos, name);

        sprite = new CharacterSprite(getImage(), new Vector2i(40, 60));
        setSize(sprite.getSize());

        maxVelocity = 7;

        maxHealth = 100;
        health = maxHealth;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(RenderSystem rs) {
        sprite.render(rs, getPos());
    }

    public void move(Vector2f touchVector, SceneMainArea scene) {
        Vector2f touchDirection = MathUtil.getUnitVector(touchVector);

        float velocityMagnitude = Math.min(MathUtil.getDistance(touchVector)/100f, maxVelocity);
        Vector2f velocity = Vector2f.multiplyVector(touchDirection, velocityMagnitude);

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
