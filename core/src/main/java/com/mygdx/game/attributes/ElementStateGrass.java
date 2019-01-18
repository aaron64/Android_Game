package com.mygdx.game.attributes;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;

public class ElementStateGrass extends ElementState implements CooldownInterface {

    private com.mygdx.game.util.Cooldown timer;
    public ElementStateGrass(BattleLiving affected) {
        super(affected);
        timer = new com.mygdx.game.util.Cooldown(this, "TIMER", false, 300);
        affected.setCanUseItem(false);
    }

    @Override
    public void update() {
        timer.update();
    }

    @Override
    public void trigger(String name) {
        affected.setCanUseItem(true);
        affected.setElementState(null);
    }
}
