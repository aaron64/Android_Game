package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;

/**
 * ActionWait
 *
 * This class locks the user when it is
 * used in the action queue for a specified
 * amount of time
 *
 * @author  Aaron Chambers
 */
public class ActionWait extends Action implements CooldownInterface {

    private Cooldown cooldown;

    /**
     * ActionWait constructor
     * @param user The BattleEntity being assigned the action
     * @param time The duration of the lock
     */
    public ActionWait(BattleLiving user, int time) {
        super(user);
        cooldown = new Cooldown(this, "LOCK", false, time);
    }

    /**
     * start
     * start the lock
     */
    @Override
    public void start() {
        ((BattleLiving)(user)).lockFor(cooldown.getDuration());
    }

    @Override
    public void update() {
        super.update();
        cooldown.update();
    }

    @Override
    public void trigger(String name) {
        finished = true;
    }
}
