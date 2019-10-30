package com.mygdx.game.animation;

import com.mygdx.game.scenes.Scene;

/**
 * Animation
 *
 * This class is used for queueing animations
 * This is an abstract of the Action type
 *
 * @author  Aaron Chambers
 */
public abstract class Animation {

    private boolean lock;
    private boolean simultaneous;
    
    private String name;

    protected boolean done;

    /**
     * Animation constructor
     * @param lock Whether the game should wait for the animation to finish
     * @param simultaneous Whether the animation should run along other animations queued
     * @param name Name of the animation
     */
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
