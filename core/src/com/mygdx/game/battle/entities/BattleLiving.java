package com.mygdx.game.battle.entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.util.MathUtil;

public abstract class BattleLiving extends BattleEntity {

    protected SceneBattleTileType[] acceptedTileTypes;

    private int health, maxHealth;
    public BattleLiving(Vector2 pos, String folder, String name, SceneBattleGrid grid) {
        super(pos, folder, name, grid);
    }

    public void hit(int dmg) {
        this.health -= dmg;
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

    public void move(Vector2 dp) {
        Vector2 newPos = MathUtil.addVec(getIndexPos(), dp);
        if(getGrid().isInBounds(newPos) && tileAccepted(getGrid().getTile(newPos))) {
            getGrid().getTile(getIndexPos()).setEntity(null);

            setIndexPos(newPos);
            setPos(getGrid().getAbsoluteTilePosition(newPos));

            getGrid().getTile(newPos).setEntity(this);
        }
    }

    public void moveUp() {
        move(new Vector2(0,1));
    }

    public void moveDown() {
        move(new Vector2(0,-1));
    }

    public void moveLeft() {
        move(new Vector2(-1,0));
    }

    public void moveRight() {
        move(new Vector2(1,0));
    }

    public boolean tileAccepted(SceneBattleTile tile) {
        for(int i = 0; i < acceptedTileTypes.length; i++) {
            if(acceptedTileTypes[i] == (tile.getTileType()))
                return true;
        }
        return false;
    }
}
