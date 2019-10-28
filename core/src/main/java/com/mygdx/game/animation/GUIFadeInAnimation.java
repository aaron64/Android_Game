package com.mygdx.game.animation;

import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.scenes.Scene;

public class GUIFadeInAnimation extends Animation {

    private GUIComponent component;
    private int time, duration;
    public GUIFadeInAnimation(int time, GUIComponent component) {
        super(false, true, "GUI_FADE_IN");

        this.component = component;
        this.component.setAlpha(0);

        this.time = time;
        this.duration = 0;
    }

    @Override
    public void update(Scene scene) {

        component.setAlpha((float) duration /time);

        duration++;

        if(duration >= time) {
            done = true;
            component.setAlpha(1f);
        }
    }
}
