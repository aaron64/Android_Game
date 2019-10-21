package com.mygdx.game.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vector2f;

public class GUIImage extends GUIHPanel {

    protected Texture texture;

    public GUIImage(GUI gui, String name, GUIComponent parent, Vector2f size, String fileName) {
        super(gui, name, parent, size);
        texture = Image.getImage("gui/" + fileName);
    }

    public GUIImage(GUI gui, String name, GUIComponent parent, Vector2f size, Texture texture) {
        super(gui, name, parent, size);
        this.texture = texture;
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        rs.setColor(1f, 1f, 1f, getAlpha());
        rs.draw(texture, pos, getSize());
        rs.resetColor();
        super.render(rs);
    }
}
