package com.mygdx.game.animation;

import com.mygdx.game.scenes.Scene;

public abstract class Animation {

    private boolean lock;
    private boolean simultaneous;
    protected boolean done;
    private String name;

    public Animation(boolean lock, boolean simultaneous, String name) {
        this.lock = lock;
        this.simultaneous = simultaneous;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
