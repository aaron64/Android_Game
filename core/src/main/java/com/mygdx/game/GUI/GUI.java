package com.mygdx.game.GUI;

import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;

public class GUI {

    private GUINode node;
    private boolean valid = false;

    public GUI() {
        node = new GUINode(this, "");
    }

    public void update(Scene scene) {
        node.update(scene);
        if(!valid) {
            setChildPos();
            valid = true;
        }
    }

    public void render(RenderSystem rs) {
        rs.beginGUI();

        node.render(rs);
    }

    public void setChildPos() {
        node.setChildPos();
    }

    public void setAbsolute() {
        GUIComponent component = node.children.remove(node.children.size() - 1);
        node.absoluteList.add(component);
    }

    public void removeAbsolute(GUIComponent component) {
        node.absoluteList.remove(component);
    }

    public void invalidate() {
        valid = false;
    }

    public GUINode getNode() {
        return node;
    }

    public void tap(float x, float y) {
        node.tap(x, y);
    }

    public void hold(float x, float y) {
        node.hold(x, y);
    }

    public void stopHold(float x, float y) {
        node.stopHold(x, y);
    }
}
