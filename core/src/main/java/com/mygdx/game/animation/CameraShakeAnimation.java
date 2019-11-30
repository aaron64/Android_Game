package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vec2f;

public class CameraShakeAnimation extends Animation {

    private OrthographicCamera camera;
    private int time, duration;
    private Vec2f origionalPos;
    public CameraShakeAnimation(RenderSystem rs) {
        super(false, true, "CAMERA_SHAKE");
        this.camera = rs.getCamera();

        origionalPos = new Vec2f(camera.position.x, camera.position.y);

        time = 0;
        duration = 5;
    }

    @Override
    public void update(Scene scene) {
        time++;
        camera.translate((time%2) * 20, 0);
        camera.update();

        if(time >= duration) {
            done = true;
            camera.position.x = origionalPos.x;
            camera.position.y = origionalPos.y;
        }
    }
}
