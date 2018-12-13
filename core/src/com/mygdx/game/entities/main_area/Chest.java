package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.scenes.main_area.SceneMainAreaTile;


public class Chest extends MainAreaInteractive {

    public Chest(SceneMainArea scene, Vector2 pos, SceneMainAreaTile tile) {
        super(scene, pos, "chest");
        setSize(tile.getSize());
    }
}
