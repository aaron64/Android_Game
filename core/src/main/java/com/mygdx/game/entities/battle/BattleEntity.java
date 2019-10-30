package com.mygdx.game.entities.battle;


import com.mygdx.game.action.ActionQueue;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

public abstract class BattleEntity extends Entity {

    private Vec2i indexPos;
    protected SceneBattle scene;
    protected ActionQueue actionQueue;

    // tile entities
    public BattleEntity(SceneBattle scene, SceneBattleGrid grid, Vec2i indexPos, String name) {
        super(grid.getAbsoluteTilePosition(indexPos), "battle", name);
        setIndexPos(indexPos);
        scene.getGrid().getTile(indexPos).setEntity(this);
        actionQueue = new ActionQueue();
        this.scene = scene;
    }

    public BattleEntity(SceneBattle scene, SceneBattleTile tile, String name) {
        super(new Vec2f(tile.getPos()), "battle", name);
        setIndexPos(tile.getIndexPos());
        tile.setEntity(this);
        actionQueue = new ActionQueue();
        this.scene = scene;
    }

    // tiles
    public BattleEntity(SceneBattle scene, SceneBattleGrid grid, Vec2i indexPos, String folder, String name) {
        super(grid.getAbsoluteTilePosition(indexPos), folder, name);
        setIndexPos(indexPos);
        actionQueue = new ActionQueue();
        this.scene = scene;
    }

    // entities not bound to tiles
    public BattleEntity(SceneBattle scene, Vec2i indexPos, String name) {
        super(scene.getGrid().getAbsoluteTilePosition(indexPos), "battle", name);
        actionQueue = new ActionQueue();
        this.scene = scene;
    }

    public void refreshIndexPos() {
        setIndexPos(scene.getGrid().getIndexPosition(getPos()));
    }

    public void hit(int dmg) {

    }

    public void hit(int dmg, Element hitElement) {

    }

    public Vec2i getIndexPos() {
        return indexPos;
    }

    public void setIndexPos(Vec2i indexPos) {
        this.indexPos = indexPos;
    }

    public ActionQueue getActionQueue() {
        return actionQueue;
    }
}
