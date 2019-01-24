package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;

public class ActionHeal extends Action {

    private int amount;
    public ActionHeal(BattleEntity user, int amount) {
        super(user);
        this.amount = amount;
    }

    @Override
    public void start() {
        ((BattleLiving)user).recover(amount);
        finished = true;
    }

    @Override
    public void update() {
        super.update();
    }
}
