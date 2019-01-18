package com.mygdx.game.entities.battle;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.factories.CardFactory;

import com.mygdx.game.factories.CardFactory;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.CooldownInterface;

public class EnemyTest extends BattleEnemy implements CooldownInterface {

    public EnemyTest(SceneBattle scene, SceneBattleTile tile, String name, int health) {
        super(scene, tile, name, health, null);
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
            cardStack.peek().use(scene, this);
        }
    }

    @Override
    public void trigger(String name) {

    }
}
