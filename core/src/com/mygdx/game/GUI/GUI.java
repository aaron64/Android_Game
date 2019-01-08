package com.mygdx.game.GUI;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.RenderSystem;

import java.util.ArrayList;

public class GUI {

    private ArrayList<GUIComponent> components;
    private ArrayList<GUIComponent> removeList;

    public GUI() {
        components = new ArrayList<GUIComponent>();
        removeList = new ArrayList<GUIComponent>();
    }

    public void update(Scene scene) {
        components.removeAll(removeList);
        removeList.clear();

        for(GUIComponent comp : components) {
            comp.update(scene);
        }
    }

    public void render(RenderSystem rs) {
        rs.beginGUI();

        for(GUIComponent comp : components) {
            comp.render(rs);
        }
    }

    public void addComponent(GUIComponent comp) {
        components.add(comp);
    }

    public void removeComponent(GUIComponent comp) {
        removeList.add(comp);
    }

    public GUIComponent getComponent(String name) {
        for(GUIComponent  comp : components) {
            if(comp.getName().equals(name))
                return comp;
        }
        return null;
    }

    public void tap(float x, float y) {
        for(GUIComponent comp : components) {
            comp.tap(x, y);
        }
    }
}
