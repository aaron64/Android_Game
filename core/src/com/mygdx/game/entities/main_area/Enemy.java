package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.main_area.SceneMainArea;

public abstract class Enemy extends MainAreaEntity {

    public Enemy(SceneMainArea scene, Vector2 pos, String name) {
        super(scene, pos, name);
    }

    @Override
    public void touch(MainAreaEntity toucher) {
        if(toucher instanceof Player) {
            scene.removeEntity(this);
            Game.pushScene(new SceneBattle());
        }
    }
}
