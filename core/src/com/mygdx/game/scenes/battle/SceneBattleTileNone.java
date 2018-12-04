package com.mygdx.game.scenes.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.RenderSystem;

public class SceneBattleTileNone extends SceneBattleTile {

    public SceneBattleTileNone(Vector2 indexPos, Vector2 offset, Vector2 size, SceneBattleGrid grid, SceneBattleTileType tileType) {
        super(indexPos, offset, size, grid, tileType);
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }
}
