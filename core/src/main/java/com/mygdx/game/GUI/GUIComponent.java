package com.mygdx.game.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

import java.util.ArrayList;

public abstract class GUIComponent {
    protected GUI gui;
    protected String name;
    protected boolean visible;


    protected GUIComponent parent;
    protected ArrayList<GUIComponent> children;


    protected Vector2f pos;
    protected Vector2i size;

    private float lMargin, rMargin, tMargin, bMargin;


    protected boolean renderBackground = true;

    private static Texture boxTL = new Texture("gui/box/boxTL.png");
    private static Texture boxT = new Texture("gui/box/boxT.png");
    private static Texture boxTR = new Texture("gui/box/boxTR.png");
    private static Texture boxL = new Texture("gui/box/boxL.png");
    private static Texture boxC = new Texture("gui/box/boxC.png");
    private static Texture boxR = new Texture("gui/box/boxR.png");
    private static Texture boxBL = new Texture("gui/box/boxBL.png");
    private static Texture boxB = new Texture("gui/box/boxB.png");
    private static Texture boxBR = new Texture("gui/box/boxBR.png");

    private static int backgroundScale = 4;


    public enum HorizontalAnchor {
        LEFT,
        RIGHT,
        CENTER
    }

    public enum VerticalAnchor {
        TOP,
        BOTTOM,
        CENTER
    }

    protected HorizontalAnchor horizontalAnchor = HorizontalAnchor.LEFT;
    protected VerticalAnchor verticalAnchor = VerticalAnchor.TOP;

    public GUIComponent(GUI gui, String name, GUIComponent parent, Vector2f size) {
        this.name = name;
        this.gui = gui;

        children = new ArrayList<GUIComponent>();

        visible = true;

        if(parent != null)
            parent.addChild(this, size);

        pos = new Vector2f(0,0);
        gui.invalidate();

        setMargin(4);

        renderBackground = true;
    }

    public void update(Scene scene) {
        for(GUIComponent child : children) {
            child.update(scene);
        }
    }

    public void render(RenderSystem rs) {
        for(GUIComponent child : children) {
            child.render(rs);
        }
    }

    public void renderBackground(RenderSystem rs) {
        if(renderBackground) {
            int cornerSize = boxTL.getWidth() * backgroundScale;
            int horizontalSize = size.w() - cornerSize * 2;
            int verticalSize = size.h() - cornerSize * 2;

            rs.draw(boxBL, pos.x, pos.y, cornerSize, cornerSize);
            rs.draw(boxB, pos.x + cornerSize, pos.y, horizontalSize, cornerSize);
            rs.draw(boxBR, pos.x + cornerSize + horizontalSize, pos.y, cornerSize, cornerSize);

            rs.draw(boxL, pos.x, pos.y + cornerSize, cornerSize, verticalSize);
            rs.draw(boxC, pos.x + cornerSize, pos.y + cornerSize, horizontalSize, verticalSize);
            rs.draw(boxR, pos.x + cornerSize + horizontalSize, pos.y + cornerSize, cornerSize, verticalSize);

            rs.draw(boxTL, pos.x, pos.y + cornerSize + verticalSize, cornerSize, cornerSize);
            rs.draw(boxT, pos.x + cornerSize, pos.y + cornerSize + verticalSize, horizontalSize, cornerSize);
            rs.draw(boxTR, pos.x + cornerSize + horizontalSize, pos.y + cornerSize + verticalSize, cornerSize, cornerSize);
        }
    }

    public void addChild(GUIComponent child, Vector2f size) {
        child.setParent(this);

        Vector2i cSize = new Vector2i(Vector2i.multiplyVectors(size, getSize()));
        if(cSize.x == 0)
            cSize.x = cSize.y;
        else if(cSize.y == 0)
            cSize.y = cSize.x;
        child.setSize(cSize);

        children.add(child);
    }

    public void addChild(GUIText child) {
        child.setParent(this);
        children.add(child);
    }

    public void tap(float x, float y) {
        for(GUIComponent child : children) {
            if(child.getPos().x < x && child.getPos().x + child.getSize().x > x) {
                if(child.getPos().y < y && child.getPos().y + child.getSize().y > y)
                    child.tap(x, y);
            }
        }
    }

    public void hold(float x, float y) {
        for(GUIComponent child : children) {
            if(child.getPos().x < x && child.getPos().x + child.getSize().x > x) {
                if(child.getPos().y < y && child.getPos().y + child.getSize().y > y)
                    child.hold(x, y);
            }
        }
    }

    public void stopHold(float x, float y) {
        for(GUIComponent child : children) {
            if(child.getPos().x < x && child.getPos().x + child.getSize().x > x) {
                if(child.getPos().y < y && child.getPos().y + child.getSize().y > y)
                    child.stopHold(x, y);
            }
        }
    }

    public abstract void setChildPos();

    public void off() {
        visible = false;
    }

    public void on() {
        visible = true;
    }

    public boolean isOn() {
        return visible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GUIComponent getParent() {
        return parent;
    }

    public void setParent(GUIComponent parent) {
        this.parent = parent;
    }


    public void setMargin(float l, float r, float t, float b) {
        lMargin = l;
        rMargin = r;
        tMargin = t;
        bMargin = b;
    }

    public void setMargin(float h, float v) {
        setMargin(h, h, v, v);
    }

    public void setMargin(float f) {
        setMargin(f, f);
    }

    public Vector2f getTotalSize() {
        return new Vector2f(size.x + lMargin + rMargin, size.y + tMargin + bMargin);
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
    }

    public float leftMargin() {
        return lMargin;
    }

    public float rightMargin() {
        return rMargin;
    }

    public float topMargin() {
        return tMargin;
    }

    public float bottomMargin() {
        return bMargin;
    }

    public void leftMargin(float lMargin) {
        this.lMargin = lMargin;
    }

    public void rightMargin(float rMargin) {
        this.rMargin = rMargin;
    }

    public void topMargin(float tMargin) {
        this.tMargin = tMargin;
    }

    public void bottomMargin(float bMargin) {
        this.bMargin = bMargin;
    }

    public void setHorizontalAnchor(GUIHPanel.HorizontalAnchor anchor) {
        horizontalAnchor = anchor;
        gui.invalidate();
    }

    public void setVerticalAnchor(GUIHPanel.VerticalAnchor verticalAnchor) {
        this.verticalAnchor = verticalAnchor;
        gui.invalidate();
    }
}
