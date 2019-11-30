package com.mygdx.game.entities.battle;


import com.mygdx.game.action.ActionAttack;
import com.mygdx.game.action.ActionJump;
import com.mygdx.game.action.ActionUseCard;
import com.mygdx.game.action.ActionWait;
import com.mygdx.game.factories.CardFactory;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.scenes.battle.SceneBattleTileType;
import com.mygdx.game.stats.BattleStats;
import com.mygdx.game.util.CooldownInterface;
import com.mygdx.game.util.Vec2i;

import java.util.ArrayList;


public class BattleEnemySwordsman extends BattleEnemy implements CooldownInterface {

    public BattleEnemySwordsman(SceneBattle scene, SceneBattleTile tile, int health) {
        super(scene, tile, "enemy", health, null, BattleStats.BASE_STATS_SWORDSMAN);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.ENEMY, SceneBattleTileType.NEUTRAL};

        setSize(scene.getGrid().getTile(0,0).getSize());

        cardStack.push(CardFactory.buildCard("Sword"));

        actionQueue.add(new ActionWait(this, battleStats.getMovementLock()));
        actionQueue.add(new ActionAttack(this));
    }

    @Override
    public void update() {
        super.update();
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
            Vec2i indexPos = getIndexPos();
            if(scene.getGrid().getTile(indexPos.x - 1, indexPos.y).getEntity() == scene.getPlayer()) {
                Card card = cardStack.peek();

                for (int i = 1; i <= 2; i++) {
                    if(scene.getGrid().getTile(getIndexPos().x - i, getIndexPos().y) != null)
                        scene.getGrid().getTile(getIndexPos().x - i, getIndexPos().y).lightUp(card.getInitialLock() * 2);
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
