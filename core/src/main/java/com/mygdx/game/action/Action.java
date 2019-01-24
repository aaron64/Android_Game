package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleEntity;

public abstract class Action {

    protected boolean finished;
    private boolean started;
    protected BattleEntity user;
    public Action(BattleEntity user) {
        this.user = user;
        finished = false;
        started = false;
    }

    public abstract void start();

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
