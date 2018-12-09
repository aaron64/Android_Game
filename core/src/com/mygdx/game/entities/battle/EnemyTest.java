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

    public EnemyTest(Vector2 pos, String name, SceneBattleGrid grid) {
        super(pos, name, grid);
        setSize(grid.getTile(0,0).getSize());
        cardStack.push(CardLoader.buildCard("Bow"));
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);

        if(!locked()) {
            SceneBattle sceneBattle = (SceneBattle)scene;
            Entity e = getDirectLineOfSight((sceneBattle.getGrid()));
            if (e instanceof BattleEntity) {
                lockFor(100);
                shoot(sceneBattle);
            }
        }
    }

    public void shoot(SceneBattle sceneBattle) {
        cardStack.peek().use(sceneBattle, this);
    }
}
