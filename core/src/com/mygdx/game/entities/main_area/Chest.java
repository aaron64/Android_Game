package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.scenes.main_area.SceneMainAreaTile;
import com.mygdx.game.util.RenderSystem;


public class Chest extends MainAreaInteractive {

    private boolean open;
    private Texture openTexture;

    public Chest(SceneMainArea scene, Vector2 pos, SceneMainAreaTile tile) {
        super(scene, pos, "chest");
        setSize(tile.getSize());
        open = false;

        openTexture = new Texture("entities/main_area/chest_open.png");
    }

    @Override
    public void clickOn() {
        if(checkDistance())
            open = true;
    }

    private void open() {

    }

    public void render(RenderSystem rs) {
        if(open) {
            rs.draw(openTexture, getPos(), getSize());
        } else {
            super.render(rs);
        }
    }
}
