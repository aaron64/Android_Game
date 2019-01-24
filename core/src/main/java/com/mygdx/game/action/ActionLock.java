package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;

public class ActionLock extends Action implements CooldownInterface {

    private Cooldown cooldown;
    public ActionLock(BattleLiving user, int time) {
        super(user);
        cooldown = new Cooldown(this, "LOCK", false, time);
    }

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
