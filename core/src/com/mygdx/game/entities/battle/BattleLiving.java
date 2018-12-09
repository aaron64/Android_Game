package com.mygdx.game.entities.battle;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
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

    public BattleLiving(Vector2 pos, String name, Facing facing, SceneBattleGrid grid, int health) {
        super(pos, name, grid);
        cardStack = new Stack<Card>();

        this.health = health;
        renderHealth = health;

        this.facing = facing;

        lockCooldown = new Cooldown(true, 10);

        healthFont = FontUtil.getFont(36);
        //healthFont.getData().setScale(5);
    }

    public void update(Scene scene) {
        lockCooldown.update();

        if(renderHealth > health) {
            renderHealth--;
        } else if(renderHealth < health) {
            renderHealth++;
        }
    }

    public void render(RenderSystem rs) {
        super.render(rs);
        rs.drawText(healthFont, ""+renderHealth, MathUtil.addVec(getPos(), new Vector2(0,getSize().y + 100)));
    }

    public void hit(int dmg, SceneBattle scene) {
        health -= dmg;
        if(health <= 0) {
            die(scene);
        }
    }

    public abstract void die(SceneBattle scene);

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
        if(!locked()) {
            Vector2 newPos = MathUtil.addVec(getIndexPos(), dp);
            if (getGrid().isInBounds(newPos) && tileAccepted(getGrid().getTile(newPos))) {
                moveTo(newPos);
            }
        }
    }

    public void moveTo(Vector2 iPos) {
        getGrid().getTile(getIndexPos()).removeEntity();
        setIndexPos(iPos);
        setPos(getGrid().getAbsoluteTilePosition(iPos));
        getGrid().getTile(iPos).setEntity(this);
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
        if(tile == null)
            return false;

        for(int i = 0; i < acceptedTileTypes.length; i++) {
            if(acceptedTileTypes[i] == (tile.getTileType()))
                return true;
        }
        return false;
    }

    public boolean facingRight() {
        return facing == Facing.RIGHT;
    }

    public boolean locked() {
        return !lockCooldown.ready();
    }

    public void lockFor(int duration) {
        lockCooldown.setDuration(duration);
        lockCooldown.reset();
    }

    public void resetLock() {
        lockCooldown.reset();
    }
}
