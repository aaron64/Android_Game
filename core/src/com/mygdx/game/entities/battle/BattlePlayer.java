package com.mygdx.game.entities.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTileType;

public class BattlePlayer extends BattleLiving  {

    public BattlePlayer(Vector2 pos, String folder, String name, SceneBattleGrid grid) {
        super(pos, folder, name, grid);
        acceptedTileTypes = new SceneBattleTileType[]{SceneBattleTileType.FRIENDLY, SceneBattleTileType.NEUTRAL};
    }

    @Override
    public void update(Scene scene) {

    }

    public void move(float vx, float vy) {
        if(Math.abs(vx) > Math.abs(vy)) {
            if(vx > 0) moveRight(); else moveLeft();
        } else {
            if(vy > 0) moveDown(); else moveUp();
        }
    }
}
