package com.mygdx.game.entities.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.items.cards.CardLoader;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.util.Cooldown;

public class EnemyTest extends BattleEnemy {

    public EnemyTest(SceneBattle scene, SceneBattleGrid grid, Vector2 pos, String name) {
        super(scene, grid, pos, name);
        setSize(scene.getGrid().getTile(0,0).getSize());
        cardStack.push(CardLoader.buildCard("Bow"));
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
}
