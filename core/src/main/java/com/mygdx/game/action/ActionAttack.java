package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;

/**
 * ActionAttafck
 *
 * Calls enemy to attack
 *
 * @author  Aaron Chambers
 */
public class ActionAttack extends Action {

    /**
     * ActionAttack constructor
     * @param user The BattleEntity being assigned the action
     */
    public ActionAttack(BattleEntity user) {
        super(user);
    }

    /**
     * start
     * enemy attacks, finish action
     */
    @Override
    public void start() {
        ((BattleEnemy)user).attack();
        finished = true;
    }
}
