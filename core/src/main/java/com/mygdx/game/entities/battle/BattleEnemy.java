package com.mygdx.game.entities.battle;


import com.mygdx.game.attributes.Element;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vector2i;

public abstract class BattleEnemy extends BattleLiving {

    public BattleEnemy(SceneBattle scene, SceneBattleTile tile, String name, int health, Element element) {
        super(scene, tile, name, Facing.LEFT, health, health, element);
        scene.enemySpawned(this);
    }

    @Override
    public void die() {
        super.die();
        scene.getTile(getIndexPos()).removeEntity();

        scene.enemyKilled(this);
    }
}
