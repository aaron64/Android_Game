package com.mygdx.game.entities.battle;

import com.mygdx.game.action.ActionAttack;
import com.mygdx.game.action.ActionJump;
import com.mygdx.game.action.ActionUseCard;
import com.mygdx.game.action.ActionWait;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.factories.CardFactory;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.stats.BattleStats;

import java.util.ArrayList;

public class BattleEnemyMage extends BattleEnemy {


    public BattleEnemyMage(SceneBattle scene, SceneBattleTile tile, int health) {
        super(scene, tile, "mage", health, Element.getRandomElement(false), BattleStats.BASE_STATS_MAGE);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.ENEMY, SceneBattleTileType.NEUTRAL};

        cardStack.push(CardFactory.buildCard("Magic", element));

        actionQueue.add(new ActionWait(this, battleStats.getMovementLock()));
        actionQueue.add(new ActionAttack(this));
    }

    @Override
    public void update() {
        super.update();

        spriteSheet.update();
    }

    @Override
    public void render(RenderSystem rs) {
        spriteSheet.render(rs, getPos());
    }

    @Override
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

        actionQueue.addNow(new ActionWait(this, battleStats.getMovementLock()));
    }

    @Override
    public void attack() {
        if(canUseItem()) {
            Entity e = getDirectLineOfSight();
            if (e instanceof BattlePlayer) {
                Card card = cardStack.peek();

                for (int i = (int) (getIndexPos().x - 1); i >= 0; i--) {
                    scene.getGrid().getTile(i, (int) getIndexPos().y).lightUp(card.getInitialLock() * 2);
                }
                actionQueue.add(new ActionWait(this, card.getInitialLock() * 2));
                actionQueue.add(new ActionUseCard(this, scene, card));
                actionQueue.add(new ActionWait(this, card.getFinalLock()));
                actionQueue.add(new ActionWait(this, battleStats.getMovementLock()));
                actionQueue.add(new ActionJump(this));
            }
        }
        actionQueue.add(new ActionJump(this));
        actionQueue.add(new ActionAttack(this));
    }

    @Override
    public void trigger(String name) {

    }
}
