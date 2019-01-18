package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.scenes.Scene;

public class ZoomAnimation extends Animation {

    private int duration, time;
    private float zoom0, zoom1;

    private OrthographicCamera camera;

    public ZoomAnimation(boolean lock, boolean simultaneous, int duration, float zoom0, float zoom1, OrthographicCamera camera) {
        super(lock, simultaneous, "ZOOM");

        this.camera = camera;

        this.duration = duration;
        time = 0;

        this.zoom0 = zoom0;
        this.zoom1 = zoom1;

        camera.zoom = zoom0;
    }

    @Override
    public void update(Scene scene) {
        time++;

        float zoom = zoom0 + (zoom1-zoom0) * ((float)time / duration);
        if(time >= duration) {
            zoom = zoom1;
            done = true;
        }

        camera.zoom = zoom;
    }
}
