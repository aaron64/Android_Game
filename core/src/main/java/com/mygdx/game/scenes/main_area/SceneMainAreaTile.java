package com.mygdx.game.scenes.main_area;

import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.util.Vector2f;

public class SceneMainAreaTile extends MainAreaEntity {

    private SceneMainAreaTileType type;

    protected int scale = 2;

    public SceneMainAreaTile(SceneMainArea scene, Vector2f pos, String folder, String name, SceneMainAreaTileType type) {
        super(scene, pos, folder, name);
        getSize().multiply(scale);
        setPos(Vector2f.multiplyVectors(getPos(), getSize()));

        this.type = type;
    }

    @Override
    public void update() {

    }


    public SceneMainAreaTileType getType() {
        return type;
    }

    public void setType(SceneMainAreaTileType type) {
        this.type = type;
    }
}
