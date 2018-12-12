package com.mygdx.game.entities.main_area;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;

public abstract class Enemy extends MainAreaEntity {

    public Enemy(Vector2 pos, String name) {
        super(pos, name);
    }

    @Override
    public void touch(MainAreaEntity toucher, Scene scene) {
        if(toucher instanceof Player) {
            scene.removeEntity(this);
            Game.pushScene(new SceneBattle());
        }
    }
}
