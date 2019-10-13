package com.mygdx.game.entities.battle;

import com.mygdx.game.action.ActionLock;
import com.mygdx.game.action.ActionUseCard;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.factories.CardFactory;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.TimedSpriteSheet;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.Vector2i;

import java.util.ArrayList;

public class BattleEnemyMage extends BattleEnemy {

    private Cooldown moveCooldown;
    private Cooldown attackCooldown;

    private TimedSpriteSheet spriteSheet;

    public BattleEnemyMage(SceneBattle scene, SceneBattleTile tile, int health) {
        super(scene, tile, "mage", health, Element.getRandomElement(false));
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.ENEMY, SceneBattleTileType.NEUTRAL};

        cardStack.push(CardFactory.buildCard("Magic", element));

        moveCooldown = new Cooldown(this, "MOVE", false, 80);
        attackCooldown = new Cooldown(this, "ATTACK", false, 100);

        scaleWidth(scene.getGrid().getTile(0, 0).getSize().x);

        spriteSheet = new TimedSpriteSheet(getImage(), 2, 100);
        spriteSheet.setSize(getSize());
    }

    @Override
    public void update() {
        super.update();

        moveCooldown.update();
        attackCooldown.update();

        spriteSheet.update();
    }

    @Override
    public void render(RenderSystem rs) {
        spriteSheet.render(rs, getPos());
    }

    public void jump() {
        ArrayList<SceneBattleTile> surroundings = scene.getGrid().getSurroundings(getIndexPos());

        boolean tileFound = false;
        int r = 0;
        while(!tileFound) {
            r = (int)(Math.random() * surroundings.size());
            if(tileAccepted(surroundings.get(r))) {
                tileFound = true;
            }
        }
        moveTo(surroundings.get(r).getIndexPos());
    }

    public void attack() {
        if(canUseItem()) {
            Card card = cardStack.peek();
            getActionQueue().add(new ActionLock(this, card.getInitialLock()));
            getActionQueue().add(new ActionUseCard(this, scene, card));
            getActionQueue().add(new ActionLock(this, card.getFinalLock()));
        }
    }

    @Override
    public void trigger(String name) {
        if(name.equals("MOVE")) {
            moveCooldown.reset();
            jump();
        } else if(name.equals("ATTACK")) {
            Vector2i indexPos = getIndexPos();
            if(scene.getGrid().getTile(indexPos.x - 1, indexPos.y).getEntity() == scene.getPlayer()) {
                attack();
                attackCooldown.reset();
            }
        } else if(name.equals("LOCK")) {

        }
    }
}
