package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.GUI.GUIHPanel;
import com.mygdx.game.GUI.GUIImage;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vec2f;

public class BattleGaugeComponent extends GUIHPanel {

    private GUIImage gagueImage;
    private GUIHPanel imageHolder;
    private int time, count;
    public BattleGaugeComponent(GUI gui, GUIComponent parent, Vec2f size) {
        super(gui, "BATTLE_GAUGE", parent, size);

        imageHolder = new GUIHPanel(gui, "IMAGE_HOLDER", this, new Vec2f(1, 1));
        gagueImage = new GUIImage(gui, "BATTLE_GAGUE_IMAGE", imageHolder, new Vec2f(0.01f, 1), Image.getImage("gui/selection_bar"));

        time = 0;
        count = 100;
    }

    @Override
    public void update(Scene scene) {
        if(time < count) {
            time++;
            gagueImage.getSize().x = (int)(imageHolder.getSize().x * ((float)time / count));
        } else {
            gagueImage.setImage(Image.getImage("gui/selection_bar_ready"));
        }
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        renderBackground(rs);
        super.render(rs);
    }

    public boolean ready() {
        return count >= time;
    }

    public void reset() {
        count = 0;
    }
}
