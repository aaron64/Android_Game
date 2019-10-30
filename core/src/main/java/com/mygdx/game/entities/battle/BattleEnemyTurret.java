package com.mygdx.game.entities.battle;

import com.mygdx.game.action.ActionWait;
import com.mygdx.game.action.ActionUseCard;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.factories.CardFactory;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.stats.BattleStats;
import com.mygdx.game.util.CooldownInterface;

public class BattleEnemyTurret extends BattleEnemy implements CooldownInterface {

    public BattleEnemyTurret(SceneBattle scene, SceneBattleTile tile, String name, int health) {
        super(scene, tile, name, health, null, BattleStats.BASE_STATS_ARROW_TURRET);
        setSize(scene.getGrid().getTile(0,0).getSize());

        cardStack.push(CardFactory.buildCard("Bow"));
    }

    @Override
    public void update() {
        super.update();

        if(!locked()) {
            Entity e = getDirectLineOfSight();
            if (e instanceof BattleEntity) {
                lockFor(100);
                shoot();
            }
        }
    }

    public void shoot() {
        if(canUseItem()) {
            Card card = cardStack.peek();
            getActionQueue().add(new ActionWait(this, card.getInitialLock()));
            getActionQueue().add(new ActionUseCard(this, scene, card));
            getActionQueue().add(new ActionWait(this, card.getFinalLock()));
        }
    }

    @Override
    public void trigger(String name) {

    }
}
