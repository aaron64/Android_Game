package com.mygdx.game.map;

import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.graphics.Window;

public class MapMainArea extends Map {

    private Vector2i backgroundSize;
    private Vector2f backgroundPos;

    public MapMainArea(String bgPath) {
        super();
        setBackground(new Texture("backgrounds/" + bgPath + ".png"));

        backgroundSize = Window.getSize();
        backgroundPos = new Vector2f(0,0);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(RenderSystem rs) {
        //rs.centerCameraOn(Vector2f.divideVector(Window.getSize(), 2));
        rs.draw(getBackground(), backgroundPos, backgroundSize);
    }

    @Override
    public void renderForeground(RenderSystem rs) {}
}
