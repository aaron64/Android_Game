package com.mygdx.game.attributes;

import com.mygdx.game.entities.battle.BattleLiving;

public class ElementStatePoison extends ElementState {

    private int count, time;
    public ElementStatePoison(BattleLiving affected) {
        super(affected);

        count = 0;
        time = 500;
    }

    @Override
    public void update() {
        count++;

        if(count % 5 == 0) {
            affected.hit(1);
        }
    }
}
