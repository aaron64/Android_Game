package com.mygdx.game.animation;

import com.mygdx.game.scenes.Scene;

public abstract class Animation {

    private boolean lock;
    private boolean simultaneous;
    protected boolean done;

    public Animation(boolean lock, boolean simultaneous) {
        this.lock = lock;
        this.simultaneous = simultaneous;
    }

    public abstract void update(Scene scene);

    public boolean locked() {
        return lock;
    }

    public boolean isSimultaneous() {
        return simultaneous;
    }

    public boolean done() {
        return done;
    }
}
