package com.mygdx.game.animation;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.Scene;

/**
 * BattleHitAnimation
 *
 * Animation for a living entity being hit (knock-back)
 *
 * @author  Aaron Chambers
 */
public class BattleHitAnimation extends Animation {

    private int direction;
    private int time, duration;
    private BattleLiving entity;

    /**
     * BattleHitAnimation constructor
     * @param direction Facing direction of the knock-back (1 for right, -1 for left)
     * @param entity Living being knocked back
     */
    public BattleHitAnimation(int direction, BattleLiving entity) {
        super(false, true, "BATTLE_HIT");

        time = 6;
        duration = 0;

        this.direction = direction;

        this.entity = entity;
    }

    /**
     * update
     * update the current knock-back, remove self when finished
     * @param scene The current scene
     */
    @Override
    public void update(Scene scene) {
        this.entity.getPos().x +=  direction;

        if(duration >= time) {
            done = true;
            this.entity.getPos().x -= direction * time;
        }
        duration++;
    }
}
