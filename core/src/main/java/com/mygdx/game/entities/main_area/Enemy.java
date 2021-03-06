package com.mygdx.game.entities.main_area;


import com.mygdx.game.Game;
import com.mygdx.game.entities.main_area.MainAreaEntity;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.Vector2f;

public abstract class Enemy extends MainAreaEntity {

    public Enemy(SceneMainArea scene, Vector2f pos, String name) {
        super(scene, pos, name);
    }

    @Override
    public void touch(MainAreaEntity toucher) {
        if(toucher instanceof Player) {
            scene.removeEntity(this);
            scene.resetInputs();
            Game.pushScene(new SceneBattle());
        }
    }
}
