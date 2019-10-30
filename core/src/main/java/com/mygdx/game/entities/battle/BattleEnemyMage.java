package com.mygdx.game.entities.battle;

import com.mygdx.game.action.ActionWait;
import com.mygdx.game.action.ActionUseCard;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.factories.CardFactory;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.stats.BattleStats;
import com.mygdx.game.util.Cooldown;

import java.util.ArrayList;

public class BattleEnemyMage extends BattleEnemy {

    private Cooldown moveCooldown;
    private Cooldown attackCooldown;


    public BattleEnemyMage(SceneBattle scene, SceneBattleTile tile, int health) {
        super(scene, tile, "mage", health, Element.getRandomElement(false), BattleStats.BASE_STATS_MAGE);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.ENEMY, SceneBattleTileType.NEUTRAL};

        cardStack.push(CardFactory.buildCard("Magic", element));

        moveCooldown = new Cooldown(this, "MOVE", false, 160);
        attackCooldown = new Cooldown(this, "ATTACK", false, 100);
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

            for (int i = (int) (getIndexPos().x - 1); i >= 0; i--) {
                scene.getGrid().getTile(i, (int) getIndexPos().y).lightUp(card.getInitialLock() * 2);
            }
            getActionQueue().add(new ActionWait(this, card.getInitialLock() * 2));
            getActionQueue().add(new ActionUseCard(this, scene, card));
            getActionQueue().add(new ActionWait(this, card.getFinalLock()));

            lockFor(card.getInitialLock() * 2 + card.getFinalLock());
        }
    }

    @Override
    public void trigger(String name) {
        if(name.equals("MOVE")) {
            if(!locked()) {
                moveCooldown.reset();
                jump();
            }
        } else if(name.equals("ATTACK")) {
            if(!locked()) {
                Entity e = getDirectLineOfSight();
                if (e instanceof BattlePlayer) {
                    attack();
                    attackCooldown.reset();
                }
            }
        } else if(name.equals("LOCK")) {

        }
    }
}
