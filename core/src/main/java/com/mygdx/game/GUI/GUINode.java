package com.mygdx.game.GUI;

import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.scenes.Scene;

import java.util.ArrayList;

public class GUINode extends GUIHPanel {

    ArrayList<GUIComponent> absoluteList;

    public GUINode(GUI gui, String name) {
        super(gui, name, null, null);
        setSize(Window.getSize());
        absoluteList = new ArrayList<GUIComponent>();
    }

    @Override
    public void update(Scene scene) {
        for(GUIComponent component : absoluteList) {
            component.update(scene);
        }
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        for(GUIComponent component : absoluteList) {
            component.render(rs);
        }
        super.render(rs);
    }
}
