package com.mygdx.game.entities.battle;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.RenderSystem;

import java.util.Stack;

public abstract class BattleLiving extends BattleEntity {

    protected enum Facing {
        RIGHT,
        LEFT
    }

    protected SceneBattleTileType[] acceptedTileTypes;

    protected Stack<Card> cardStack;

    private int health, maxHealth;
    private int renderHealth;
    private BitmapFont healthFont;

    private Facing facing;

    private Cooldown lockCooldown;

    public BattleLiving(SceneBattle scene, SceneBattleGrid grid, Vector2 pos, String name, Facing facing, int health) {
        super(scene, grid, pos, name);
        cardStack = new Stack<Card>();

        this.health = health;
        renderHealth = health;

        this.facing = facing;

        lockCooldown = new Cooldown(true, 10);

        healthFont = FontUtil.getFont(36);
    }

    public void update() {
        lockCooldown.update();
        updateRenderHealth();
    }

    public void render(RenderSystem rs) {
        super.render(rs);
        rs.drawText(healthFont, ""+renderHealth, MathUtil.addVec(getPos(), new Vector2(0,getSize().y + 100)));
    }

    private void updateRenderHealth() {
        if(renderHealth > health) {
            renderHealth--;
        } else if(renderHealth < health) {
            renderHealth++;
        }
    }

    public void move(Vector2 dp) {
        if(!locked()) {
            Vector2 newPos = MathUtil.addVec(getIndexPos(), dp);
            if (scene.getGrid().isInBounds(newPos) && tileAccepted(scene.getGrid().getTile(newPos))) {
                moveTo(newPos);
            }
        }
    }

    public void moveTo(Vector2 iPos) {
        scene.getGrid().getTile(getIndexPos()).removeEntity();
        setIndexPos(iPos);
        setPos(scene.getGrid().getAbsoluteTilePosition(iPos));
        scene.getGrid().getTile(iPos).setEntity(this);
    }

    public BattleEntity getDirectLineOfSight() {
        Vector2 indexPos = getIndexPos();

        if(facing == Facing.LEFT) {
            for (int i = (int) (indexPos.x - 1); i >= 0; i--) {
                Entity e = scene.getGrid().getTile(i, (int) indexPos.y).getEntity();
                if (e != null) {
                    return (BattleEntity)e;
                }
            }
        } else if (facing == Facing.RIGHT) {
            for (int i = (int) (indexPos.x + 1); i < scene.getGrid().getWidth(); i++) {
                Entity e = scene.getGrid().getTile(i, (int) indexPos.y).getEntity();
                if (e != null) {
                    return (BattleEntity)e;
                }
            }
        }
        return null;
    }

    public boolean tileAccepted(SceneBattleTile tile) {
        if(tile == null)
            return false;

        for(int i = 0; i < acceptedTileTypes.length; i++) {
            if(acceptedTileTypes[i] == (tile.getTileType()))
                return true;
        }
        return false;
    }

    public void lockFor(int duration) {
        lockCooldown.setDuration(duration);
        lockCooldown.reset();
    }

    @Override
    public void hit(int dmg) {
        health -= dmg;
        if(health <= 0) {
            die();
        }
    }

    public abstract void die();

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

    public boolean facingRight() {
        return facing == Facing.RIGHT;
    }

    public boolean locked() {
        return !lockCooldown.ready();
    }

    public void resetLock() {
        lockCooldown.reset();
    }
}
