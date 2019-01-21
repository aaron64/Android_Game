package com.mygdx.game.entities.battle;


import com.mygdx.game.attributes.Element;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public abstract class BattleEntity extends Entity {

    private Vector2i indexPos;
    protected SceneBattle scene;

    // tile entities
    public BattleEntity(SceneBattle scene, SceneBattleGrid grid, Vector2i indexPos, String name) {
        super(grid.getAbsoluteTilePosition(indexPos), "battle", name);
        setIndexPos(indexPos);
        scene.getGrid().getTile(indexPos).setEntity(this);
        this.scene = scene;
    }

    public BattleEntity(SceneBattle scene, SceneBattleTile tile, String name) {
        super(new Vector2f(tile.getPos()), "battle", name);
        setIndexPos(tile.getIndexPos());
        tile.setEntity(this);
        this.scene = scene;
    }

    // tiles
    public BattleEntity(SceneBattle scene, SceneBattleGrid grid, Vector2i indexPos, String folder, String name) {
        super(grid.getAbsoluteTilePosition(indexPos), folder, name);
        setIndexPos(indexPos);
        this.scene = scene;
    }

    // entities not bound to tiles
    public BattleEntity(SceneBattle scene, Vector2i indexPos, String name) {
        super(scene.getGrid().getAbsoluteTilePosition(indexPos), "battle", name);
        this.scene = scene;
    }

    public void refreshIndexPos() {
        setIndexPos(scene.getGrid().getIndexPosition(getPos()));
    }

    public void hit(int dmg) {

    }

    public void hit(int dmg, Element hitElement) {

    }

    public Vector2i getIndexPos() {
        return indexPos;
    }

    public void setIndexPos(Vector2i indexPos) {
        this.indexPos = indexPos;
    }
}
