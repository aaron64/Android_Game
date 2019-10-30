package com.mygdx.game.scenes.battle;


import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

public class SceneBattleTileNone extends SceneBattleTile {

    public SceneBattleTileNone(SceneBattle scene, SceneBattleGrid grid, Vec2i indexPos, Vec2f offset, Vec2i size, SceneBattleTileType tileType) {
        super(scene, grid, indexPos, offset, size, tileType);
    }

    @Override
    public void update() {
        super.update();
    }
}
