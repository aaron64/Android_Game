package com.mygdx.game.scenes.battle;


import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class SceneBattleTileNone extends SceneBattleTile {

    public SceneBattleTileNone(SceneBattle scene, SceneBattleGrid grid, Vector2i indexPos, Vector2f offset, Vector2i size, SceneBattleTileType tileType) {
        super(scene, grid, indexPos, offset, size, tileType);
    }

    @Override
    public void update() {
        super.update();
    }
}
