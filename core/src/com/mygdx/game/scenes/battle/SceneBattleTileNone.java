package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.RenderSystem;

public class SceneBattleTileNone extends SceneBattleTile {

    public SceneBattleTileNone(SceneBattle scene, SceneBattleGrid grid, Vector2 indexPos, Vector2 offset, Vector2 size, SceneBattleTileType tileType) {
        super(scene, grid, indexPos, offset, size, tileType);
    }

    @Override
    public void update() {
        super.update();
    }
}
