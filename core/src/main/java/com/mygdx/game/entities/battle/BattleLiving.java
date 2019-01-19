package com.mygdx.game.entities.battle;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.mygdx.game.GUI.components.BattleHealthComponent;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.ElementState;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2i;

import java.util.Stack;

public abstract class BattleLiving extends BattleTileEntity implements CooldownInterface {

    protected enum Facing {
        RIGHT,
        LEFT
    }

    private Facing facing;

    protected SceneBattleTileType[] acceptedTileTypes;

    protected Stack<Card> cardStack;

    private int health, maxHealth;
    private BattleHealthComponent healthComponent;

    protected ElementState elementState;
    protected Element element;

    private Cooldown lockCooldown;

    private boolean canUseItem;

    public BattleLiving(SceneBattle scene, SceneBattleTile tile, String name, Facing facing, int health, int maxHealth, Element element) {
        super(scene, tile, name);
        cardStack = new Stack<Card>();

        this.health = health;
        this.maxHealth = maxHealth;

        this.facing = facing;

        lockCooldown = new Cooldown(this, "LOCK", true, 10);

        elementState = null;

        canUseItem = true;

        healthComponent = new BattleHealthComponent(scene.getGUI(), this);
        scene.getGUI().addComponent(healthComponent);
    }

    public void update() {
        lockCooldown.update();

        if(elementState != null) {
            elementState.update();
        }
    }

    public void render(RenderSystem rs) {
        super.render(rs);
    }

    public void move(Vector2i dp) {
        if(!locked()) {
            Vector2i newPos = Vector2i.addVectors(getIndexPos(), dp);
            SceneBattleTile newTile = scene.getGrid().getTile(newPos);

            if (scene.getGrid().isInBounds(newPos) && tileAccepted(newTile)) {
                moveTo(newPos);
            }
        }
    }

    public void moveTo(Vector2i iPos) {
        scene.getGrid().getTile(getIndexPos()).removeEntity();

        setIndexPos(iPos);
        setPos(scene.getGrid().getAbsoluteTilePosition(iPos));

        scene.getGrid().getTile(iPos).setEntity(this);
    }

    public BattleEntity getDirectLineOfSight() {
        Vector2i indexPos = getIndexPos();

        if(facing == Facing.LEFT) {
            for (int i = indexPos.x - 1; i >= 0; i--) {
                Entity e = scene.getGrid().getTile(i, indexPos.y).getEntity();
                if (e != null) {
                    return (BattleEntity)e;
                }
            }
        } else if (facing == Facing.RIGHT) {
            for (int i = indexPos.x + 1; i < scene.getGrid().getWidth(); i++) {
                Entity e = scene.getGrid().getTile(i, indexPos.y).getEntity();
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
            if(acceptedTileTypes[i] == (tile.getTileType()) && tile.getEntity() == null)
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

    @Override
    public void hit(int dmg, Element hitElement) {
        if(hitElement != null) {
            dmg *= hitElement.getMultiplier(element);
            if (elementState == null) {
                elementState = ElementState.generateElementState(hitElement, this);
            }
        }
        hit(dmg);
    }

    public void die() {
        scene.getGUI().removeComponent(healthComponent);
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

    public void moveUp() {
        move(new Vector2i(0,1));
    }

    public void moveDown() {
        move(new Vector2i(0,-1));
    }

    public void moveLeft() {
        move(new Vector2i(-1,0));
    }

    public void moveRight() {
        move(new Vector2i(1,0));
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

    public ElementState getElementState() {
        return elementState;
    }

    public void setElementState(ElementState elementState) {
        this.elementState = elementState;
    }

    public boolean canUseItem() {
        return canUseItem;
    }

    public void setCanUseItem(boolean canUseItem) {
        this.canUseItem = canUseItem;
    }
}
