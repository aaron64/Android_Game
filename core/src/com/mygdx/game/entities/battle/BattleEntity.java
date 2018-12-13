package com.mygdx.game.entities.battle;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;

public abstract class BattleEntity extends Entity {

    private Vector2 indexPos;
    protected SceneBattle scene;

    public BattleEntity(SceneBattle scene, Vector2 indexPos, String name) {
        super(scene.getGrid().getAbsoluteTilePosition(indexPos), "battle", name);
        setIndexPos(indexPos);
        scene.getGrid().getTile(indexPos).setEntity(this);
        this.scene = scene;
    }

    public BattleEntity(SceneBattle scene, Vector2 indexPos, String folder, String name) {
        super(scene.getGrid().getAbsoluteTilePosition(indexPos), folder, name);
        setIndexPos(indexPos);
        this.scene = scene;
    }

    public void refreshIndexPos() {
        setIndexPos(scene.getGrid().getIndexPosition(getPos()));
    }

    public void hit(int dmg) {

    }

    public Vector2 getIndexPos() {
        return indexPos;
    }

    public void setIndexPos(Vector2 indexPos) {
        this.indexPos = indexPos;
    }
}
