package com.mygdx.game.GUI.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public abstract class ButtonComponent extends GUIComponent {

    private Texture icon;
    private Vector2f pos;
    protected Vector2i size;

    private Texture  renderButton;

    public static Texture button = new Texture("gui/button/button.png");
    public static Texture buttonPressed = new Texture("gui/button/button_pressed.png");

    private static int scale = 4;

    public ButtonComponent(GUI gui, String name, Vector2f pos) {
        super(gui, name);
        this.pos = pos;

        this.icon = new Texture("gui/button/" + name + ".png");

        this.size = new Vector2i(button.getWidth() * scale, button.getHeight() * scale);

        renderButton = button;
    }

    @Override
    public void update(Scene scene) {

    }

    @Override
    public void render(com.mygdx.game.graphics.RenderSystem rs) {
        if(isOn()) {
            rs.draw(renderButton, pos, size);
            rs.draw(icon, pos, size);
        }
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

    public abstract void onClick();

    @Override
    public void tap(float x, float y) {
        if(isOn()) {
            if (new Rectangle(pos.x, pos.y, size.w(), size.h()).contains(x, y)) {
                onClick();
                renderButton = buttonPressed;
            }
        }
    }
}
