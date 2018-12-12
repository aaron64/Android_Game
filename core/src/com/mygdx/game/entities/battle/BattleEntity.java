package com.mygdx.game.entities.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;

public abstract class BattleEntity extends Entity {

    private Vector2 indexPos;
    private SceneBattleGrid grid;

    public BattleEntity(Vector2 indexPos, String name, SceneBattleGrid grid) {
        super(grid.getAbsoluteTilePosition(indexPos), "battle", name);
        setIndexPos(indexPos);
        this.grid = grid;
    }

    public BattleEntity(Vector2 indexPos, String folder, String name, SceneBattleGrid grid) {
        super(grid.getAbsoluteTilePosition(indexPos), folder, name);
        setIndexPos(indexPos);
        this.grid = grid;
    }

    public void refreshIndexPos(SceneBattleGrid grid) {
        setIndexPos(grid.getIndexPosition(getPos()));
    }

    public void hit(int dmg, SceneBattle scene) {

    }

    public Vector2 getIndexPos() {
        return indexPos;
    }

    public void setIndexPos(Vector2 indexPos) {
        this.indexPos = indexPos;
    }

    public SceneBattleGrid getGrid() {
        return grid;
    }

    public void setGrid(SceneBattleGrid grid) {
        this.grid = grid;
    }
}
