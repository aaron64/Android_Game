package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleEntity;

/**
 * Action
 *
 * This class is used for queueing actions in SceneBattle
 * This is an abstract of the Action type
 *
 * @author  Aaron Chambers
 */
public abstract class Action {

    protected boolean finished;
    private boolean started;
    
    protected BattleEntity user;

    /**
     * Action constructor
     * @param user The BattleEntity being assigned the action
     */
    public Action(BattleEntity user) {
        this.user = user;
        finished = false;
        started = false;
    }

    /**
     * start
     * triggered once on first update
     */
    public abstract void start();

    /**
     * update
     * checks if action has started, if not,
     * starts the action
     */
    public void update() {
        if(!started) {
            start();
            started = true;
        }
    }

    public boolean finished() {
        return finished;
    }
}
