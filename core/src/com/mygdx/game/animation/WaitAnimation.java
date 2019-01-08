package com.mygdx.game.animation;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Cooldown;
import com.mygdx.game.util.CooldownInterface;

public class WaitAnimation extends Animation implements CooldownInterface {

    Cooldown waitCooldown;
    public WaitAnimation(boolean lock, boolean simultaneous, int waitTime) {
        super(lock, simultaneous, "WAIT");
        waitCooldown = new Cooldown(this, "WAIT", false, waitTime);
    }

    @Override
    public void update(Scene scene) {
        waitCooldown.update();
    }

    @Override
    public void trigger(String name) {
        done = true;
    }
}
