package com.mygdx.game.entities.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;

public abstract class BattleEnemy extends BattleLiving {

    public BattleEnemy(Vector2 pos, String name, SceneBattleGrid grid) {
        super(pos, name, Facing.LEFT, grid, 40);
    }

    protected Entity getDirectLineOfSight(SceneBattleGrid grid) {
        Vector2 indexPos = getIndexPos();
        for(int i = (int) (indexPos.x-1); i >= 0; i--) {
            Entity e = grid.getTile(i, (int) indexPos.y).getEntity();
            if(e != null) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void die(SceneBattle scene) {
        Vector2 indexPos = getIndexPos();
        scene.getTile((int) indexPos.x, (int) indexPos.y).removeEntity();

        scene.enemyKilled();
    }
}
