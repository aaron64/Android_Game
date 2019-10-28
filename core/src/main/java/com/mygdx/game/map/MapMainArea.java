package com.mygdx.game.map;

import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class MapMainArea extends Map {

    private Vector2i backgroundSize;
    private Vector2f backgroundPos;

    public MapMainArea(String bgPath) {
        super();
        setBackground(Image.getImage("backgrounds/" + bgPath));

        backgroundSize = Window.getSize();
        backgroundPos = new Vector2f();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(RenderSystem rs) {
        //rs.centerCameraOn(Vector2f.divideVector(Window.getSize(), 2));
        rs.drawStatic(getBackground(), backgroundPos, backgroundSize);
    }

    @Override
    public void renderForeground(RenderSystem rs) {}
}
