package com.mygdx.game.entities.battle;


import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.util.Vector2i;

public abstract class BattleEnemy extends BattleLiving {

    public BattleEnemy(SceneBattle scene, SceneBattleGrid grid, Vector2i pos, String name) {
        super(scene, grid, pos, name, Facing.LEFT, 40);
    }

    @Override
    public void die() {
        Vector2i indexPos = getIndexPos();
        scene.getTile((int) indexPos.x, (int) indexPos.y).removeEntity();

        scene.enemyKilled();
    }
}
