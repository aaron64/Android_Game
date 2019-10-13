package com.mygdx.game.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vector2f;

public class GUIButton extends GUIHPanel {

    private GUIImage image;
    private Scene scene;

    private boolean held = false;

    public GUIButton(GUI gui, String name, GUIComponent parent, Vector2f size, Texture texture, Scene scene) {
        super(gui, name, parent, size);
        setHorizontalAnchor(HorizontalAnchor.CENTER);
        setVerticalAnchor(VerticalAnchor.CENTER);
        image = new GUIImage(gui, name, this, new Vector2f(0.8f, 0.8f), texture);

        this.scene = scene;
    }

    @Override
    public void update(Scene scene) {
        super.update(scene);
    }

    @Override
    public void render(RenderSystem rs) {
        if(held)
            rs.setColor(0.8f, 0.8f, 0.8f, 1);
        renderBackground(rs);
        rs.setColor(1, 1, 1, 1);
        super.render(rs);
    }

    @Override
    public void hold(float x, float y) {
        held = true;
    }

    @Override
    public void stopHold(float x, float y) {
        held = false;
    }

    @Override
    public void tap(float x, float y) {
        scene.buttonPress(name);
    }
}
