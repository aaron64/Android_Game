package com.mygdx.game.attributes;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;

public class ElementStateShock extends ElementState implements CooldownInterface {

    private Cooldown timer;
    public ElementStateShock(BattleLiving affected) {
        super(affected);

        timer = new Cooldown(this, "TIMER", false, 200);
        affected.lockFor(200);
    }

    @Override
    public void update() {
        timer.update();
    }

    @Override
    public void trigger(String name) {
        affected.setElementState(null);
    }
}
