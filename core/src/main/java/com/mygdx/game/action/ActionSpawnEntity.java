package com.mygdx.game.action;

import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.scenes.battle.SceneBattle;

/**
 * ActionSpawnEntity
 *
 * This class spawns an entity when
 * called by the action queue
 *
 * @author  Aaron Chambers
 */
public class ActionSpawnEntity extends Action {

    private SceneBattle scene;
    private Entity e;

    /**
     * ActionSpawnEntity constructor
     * @param user The BattleEntity being assigned the action
     * @param scene Instance of SceneBattle, used to add the entity to the scene
     * @param e The entity being added to the scene
     */
    public ActionSpawnEntity(BattleEntity user, SceneBattle scene, Entity e) {
        super(user);

        this.scene = scene;
        this.e = e;
    }

    /**
     * start
     * spawn the entity, finish action
     */
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
