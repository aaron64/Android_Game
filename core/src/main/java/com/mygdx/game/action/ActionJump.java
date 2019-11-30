package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;

/**
 * ActionJump
 *
 * Calls enemy to jump
 *
 * @author  Aaron Chambers
 */
public class ActionJump extends Action {

    /**
     * ActionJump constructor
     * @param user The BattleEntity being assigned the action
     */
    public ActionJump(BattleEntity user) {
        super(user);
    }

    /**
     * start
     * enemy jumps, finish action
     */
    @Override
    public void start() {
        ((BattleEnemy)user).jump();
        finished = true;
    }
}
