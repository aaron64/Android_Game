package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.scenes.main_area.SceneMainAreaTile;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Vector2f;


public class Chest extends MainAreaInteractive {

    private boolean open;
    private Texture openTexture;

    public Chest(SceneMainArea scene, Vector2f pos, SceneMainAreaTile tile) {
        super(scene, pos, "chest");
        setSize(tile.getSize());
        open = false;

        openTexture = new Texture("entities/main_area/chest_open.png");
    }

    @Override
    public void update() {

    }

    @Override
    public void clickOn() {
        if(checkDistance()) {
            open = true;
            for(int i = 0; i < 4; i++) {
                scene.addEntity(new HealthBlob(scene, new Vector2f(getPos())));
            }
        }
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
