package com.mygdx.game.entities.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;

public abstract class BattleEnemy extends BattleLiving {

    public BattleEnemy(SceneBattle scene, SceneBattleGrid grid, Vector2 pos, String name) {
        super(scene, grid, pos, name, Facing.LEFT, 40);
    }

    @Override
    public void die() {
        Vector2 indexPos = getIndexPos();
        scene.getTile((int) indexPos.x, (int) indexPos.y).removeEntity();

        scene.enemyKilled();
    }
}
