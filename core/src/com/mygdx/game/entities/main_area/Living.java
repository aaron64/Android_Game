package com.mygdx.game.entities.main_area;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.util.Vector2f;

public abstract class Living extends Entity {

    private int health, maxHealth;
    public Living(Vector2f pos, String folder, String name) {
        super(pos, folder, name);
    }

    public void recover(int rec) {
        this.health += rec;
    }

    public int getHealth() {
        return health;
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
}
