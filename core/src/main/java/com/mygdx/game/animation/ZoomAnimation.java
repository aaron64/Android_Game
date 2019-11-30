package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.scenes.Scene;

/**
 * ZoomAnimation
 *
 * Animation that zooms the camera
 * to a specified z position
 *
 * @author  Aaron Chambers
 */
public class ZoomAnimation extends Animation {

    private int duration, time;
    private float zoom0, zoom1;

    private OrthographicCamera camera;

    /**
     * WaitAnimation constructor
     * @param lock Whether the game should wait for the animation to finish
     * @param simultaneous Whether the animation should run along other animations queued
     * @param duration Time to zoom
     * @param zoom0 Start zoom pos
     * @param zoom1 Final zoom pos
     * @param camera camera being used to zoom
     */
    public ZoomAnimation(boolean lock, boolean simultaneous, int duration, float zoom0, float zoom1, OrthographicCamera camera) {
        super(lock, simultaneous, "ZOOM");

        this.camera = camera;

        this.duration = duration;
        time = 0;

        this.zoom0 = zoom0;
        this.zoom1 = zoom1;

        camera.zoom = zoom0;
    }

    /**
     * update
     * interpolates camera zoom
     * @param scene The current scene
     */
    @Override
    public void update(Scene scene) {
        time++;

        float zoom = zoom0 + (zoom1-zoom0) * ((float)time / duration);
        if(time >= duration) {
            zoom = zoom1;
            done = true;
        }

        camera.zoom = zoom;
        camera.update();
    }
}
