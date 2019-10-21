package com.mygdx.game.animation;

import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.scenes.Scene;

public class GUIFadeInAnimation extends Animation {

    private GUIComponent component;
    private int time, count;
    public GUIFadeInAnimation(int time, GUIComponent component) {
        super(false, true, "GUI_FADE_IN");

        this.component = component;

        this.time = time;
        this.count = 0;
    }

    @Override
    public void update(Scene scene) {

        component.setAlpha((float)count/time);

        count++;

        if(count >= time) {
            done = true;
            component.setAlpha(1f);
        }
    }
}
