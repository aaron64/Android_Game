package com.mygdx.game.animation;

import com.mygdx.game.Game;
import com.mygdx.game.scenes.Scene;

public class OpenAnimation extends Animation {

    private Scene newScene;
    public OpenAnimation(boolean lock, boolean simultaneous, Scene scene) {
        super(lock, simultaneous, "OPEN");
        this.newScene = scene;
    }

    @Override
    public void update(Scene scene) {
        Game.pushScene(newScene);
        done = true;
    }
}
