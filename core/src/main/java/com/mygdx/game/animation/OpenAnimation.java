package com.mygdx.game.animation;

import com.mygdx.game.Game;
import com.mygdx.game.scenes.Scene;

/**
 * OpenAnimation
 *
 * Animation to change the current scene when called
 *
 * @author  Aaron Chambers
 */
public class OpenAnimation extends Animation {

    private Scene newScene;

    /**
     * OpenAnimation constructor
     * @param lock Whether the game should wait for the animation to finish
     * @param simultaneous Whether the animation should run along other animations queued
     * @param scene The new scene being pushed
     */
    public OpenAnimation(boolean lock, boolean simultaneous, Scene scene) {
        super(lock, simultaneous, "OPEN");
        this.newScene = scene;
    }

    /**
     * update
     * push newScene to the game's scene stack
     * @param scene The current scene
     */
    @Override
    public void update(Scene scene) {
        Game.pushScene(newScene);
        done = true;
    }
}
