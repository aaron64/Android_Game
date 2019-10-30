package com.mygdx.game.map;

import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.util.Vec2f;
import com.mygdx.game.util.Vec2i;

public class MapMainArea extends Map {

    private Vec2i backgroundSize;
    private Vec2f backgroundPos;

    public MapMainArea(String bgPath) {
        super();
        setBackground(Image.getImage("backgrounds/" + bgPath));

        backgroundSize = Window.getSize();
        backgroundPos = new Vec2f();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(RenderSystem rs) {
        //rs.centerCameraOn(Vec2f.divideVector(Window.getSize(), 2));
        rs.drawStatic(getBackground(), backgroundPos, backgroundSize);
    }

    @Override
    public void renderForeground(RenderSystem rs) {}
}
