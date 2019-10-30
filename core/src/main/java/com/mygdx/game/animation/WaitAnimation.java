package com.mygdx.game.animation;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;

/**
 * WaitAnimation
 *
 * Animation used for pause
 *
 * @author  Aaron Chambers
 */
public class WaitAnimation extends Animation implements CooldownInterface {

    private Cooldown waitCooldown;

    /**
     * WaitAnimation constructor
     * @param lock Whether the game should wait for the animation to finish
     * @param simultaneous Whether the animation should run along other animations queued
     * @param waitTime Time to wait
     */
    public WaitAnimation(boolean lock, boolean simultaneous, int waitTime) {
        super(lock, simultaneous, "WAIT");
        waitCooldown = new Cooldown(this, "WAIT", false, waitTime);
    }

    /**
     * update
     * update the cooldown timer
     * @param scene The current scene
     */
    @Override
    public void update(Scene scene) {
        waitCooldown.update();
    }

    /**
     * trigger
     * called when cooldown is finished
     * @param name Name of the cooldown timer
     */
    @Override
    public void trigger(String name) {
        done = true;
    }
}
