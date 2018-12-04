package com.mygdx.game.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Window;

public class MapMainArea extends Map {

    private Vector2 backgroundSize;
    private Vector2 backgroundPos;

    public MapMainArea(String bgPath) {
        super();
        setBackground(new Texture("backgrounds/" + bgPath + ".png"));

        backgroundPos = new Vector2(0,0);
        backgroundSize = Window.getSize();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(RenderSystem rs) {
        rs.draw(getBackground(), backgroundPos, backgroundSize);
    }
}
