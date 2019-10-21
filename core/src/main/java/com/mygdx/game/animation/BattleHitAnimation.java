package com.mygdx.game.animation;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.Scene;

public class BattleHitAnimation extends Animation {

    private int direction;
    private int time, count;
    private BattleLiving entity;

    public BattleHitAnimation(int direction, BattleLiving entity) {
        super(false, true, "BATTLE_HIT");

        time = 6;
        count = 0;

        this.direction = direction;

        this.entity = entity;
    }

    @Override
    public void update(Scene scene) {
        this.entity.getPos().x +=  direction;

        if(count >= time) {
            done = true;
            this.entity.getPos().x -= direction * time;
        }
        count++;
    }
}
