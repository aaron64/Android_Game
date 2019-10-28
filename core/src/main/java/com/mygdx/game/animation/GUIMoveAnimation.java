package com.mygdx.game.animation;

import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vector2f;

public class GUIMoveAnimation extends Animation {

    private Vector2f offset;
    private GUIComponent component;
    private int time, duration;

    public GUIMoveAnimation(Vector2f offset, int time, GUIComponent component) {
        super(false, true, "GUI_MOVE_IN");

        this.offset = offset;
        this.offset.x *= 1f/time;
        this.offset.y *= 1f/time;

        this.time = time;
        this.duration = 0;

        this.component = component;
    }

    @Override
    public void update(Scene scene) {
        component.getPos().add(offset);
        component.setChildPos();

        duration++;

        if(duration >= time) {
            done = true;
        }
    }
}
