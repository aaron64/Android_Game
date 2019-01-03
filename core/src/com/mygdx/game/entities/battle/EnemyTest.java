package com.mygdx.game.entities.battle;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.items.cards.CardBuilder;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.util.CooldownInterface;
import com.mygdx.game.util.Vector2i;

public class EnemyTest extends BattleEnemy implements CooldownInterface {

    public EnemyTest(SceneBattle scene, SceneBattleGrid grid, Vector2i pos, String name, int health) {
        super(scene, grid, pos, name, health);
        setSize(scene.getGrid().getTile(0,0).getSize());

        //TODO
        cardStack.push(CardBuilder.buildCard("Bow"));
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
        cardStack.peek().use(scene, this);
    }

    @Override
    public void trigger(String name) {

    }
}
