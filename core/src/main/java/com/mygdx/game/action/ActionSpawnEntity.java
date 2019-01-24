package com.mygdx.game.action;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.scenes.battle.SceneBattle;

public class ActionSpawnEntity extends Action {

    private SceneBattle scene;
    private Entity e;
    public ActionSpawnEntity(BattleEntity user, SceneBattle scene, Entity e) {
        super(user);

        this.scene = scene;
        this.e = e;
    }

    @Override
    public void start() {
        scene.addEntity(e);
        finished = true;
    }

    @Override
    public void update() {
        super.update();
    }
}
