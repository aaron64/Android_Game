package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.MathUtil;

public class SceneMainAreaTile extends MainAreaEntity {

    private SceneMainAreaTileType type;
    public SceneMainAreaTile(SceneMainArea scene, Vector2 pos, String name, SceneMainAreaTileType type) {
        super(scene, pos, "tiles", name);
        setPos(MathUtil.multiplyVec(getPos(), getSize()));
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
