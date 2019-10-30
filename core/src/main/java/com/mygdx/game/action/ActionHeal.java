package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;

/**
 * ActionHeal
 *
 * This class heals the user when
 * it is used in the action queue
 *
 * @author  Aaron Chambers
 */
public class ActionHeal extends Action {

    private int amount;

    /**
     * ActionHeal constructor
     * @param user The BattleEntity being assigned the action
     * @param amount The amount of health points to regenerate
     */
    public ActionHeal(BattleEntity user, int amount) {
        super(user);
        this.amount = amount;
    }

    /**
     * start
     * heal the user, finish action
     */
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
