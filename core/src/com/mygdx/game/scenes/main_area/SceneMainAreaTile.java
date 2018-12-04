package com.mygdx.game.scenes.main_area;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.MathUtil;

public class SceneMainAreaTile extends Entity {

    private SceneMainAreaTileType type;
    public SceneMainAreaTile(Vector2 pos, String folder, String name, SceneMainAreaTileType type) {
        super(pos, folder, name);
        setPos(MathUtil.multiplyVec(getPos(), getSize()));
    }

    @Override
    public void update(Scene scene) {

    }

    public SceneMainAreaTileType getType() {
        return type;
    }

    public void setType(SceneMainAreaTileType type) {
        this.type = type;
    }
}
