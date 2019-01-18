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

        backgroundPos = new Vector2f(0,0);
        backgroundSize = Window.getSize();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(com.mygdx.game.graphics.RenderSystem rs) {
        rs.draw(getBackground(), backgroundPos, backgroundSize);
    }
}
