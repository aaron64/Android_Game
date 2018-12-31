package com.mygdx.game.GUI.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.GUI.GUIComponent;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public abstract class ButtonComponent extends GUIComponent {

    private Texture icon;
    private Vector2f pos;
    private Vector2i size;

    public ButtonComponent(GUI gui, String name, Vector2f pos) {
        super(gui, name);
        this.pos = pos;

        this.icon = new Texture("gui/button/" + name + ".png");

        this.size = new Vector2i(icon.getWidth() * 4, icon.getHeight() * 4);
    }

    @Override
    public void update(Scene scene) {

    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(icon, pos, size);
    }

    public abstract void onClick();

    @Override
    public void tap(float x, float y) {
        if(new Rectangle(pos.x, pos.y, size.w(), size.h()).contains(x, y)) {
            onClick();
        }
    }
}
