package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.graphics.Texture;

import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.scenes.main_area.SceneMainAreaTile;
import com.mygdx.game.util.Vector2f;


public class Chest extends MainAreaInteractive {

    private boolean open;
    private Texture openTexture;

    public Chest(com.mygdx.game.scenes.main_area.SceneMainArea scene, Vector2f pos, SceneMainAreaTile tile) {
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
            open();
        }
    }

    private void open() {
        open = true;
        for(int i = 0; i < 4; i++) {
            //TODO
            setImage(openTexture);
            scene.addEntity(new HealthBlob(scene, new Vector2f(getPos())));
        }
    }
}
