package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.main_area.SceneMainAreaTile;


public class Chest extends MainAreaInteractive {

    public Chest(Vector2 pos, SceneMainAreaTile tile) {
        super(pos, "chest");
        setSize(tile.getSize());
    }
}
