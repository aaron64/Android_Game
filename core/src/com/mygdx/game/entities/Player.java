package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Game;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.main_area.SceneMainArea;
import com.mygdx.game.util.MathUtil;
import com.mygdx.game.util.RenderSystem;
import com.mygdx.game.util.Window;

public class Player extends MainAreaEntity {

    float maxVelocity;
    public Player(Vector2 pos, String name) {
        super(pos, name);
        maxVelocity = 7;
    }

    @Override
    public void update(Scene scene) {
        MainAreaEntity collision = ((SceneMainArea) scene).getCollision(this);
        if(collision != null) {
            Game.pushScene(new SceneBattle());
        }
    }

    public void move(Vector2 touchVector) {
        touchVector.y *= -1;
        Vector2 touchDirection = MathUtil.getUnitVector(touchVector);

        float velocity = Math.min(MathUtil.getDistance(touchVector)/100f, maxVelocity);
        Gdx.app.log("INFO", ""+velocity);
        Vector2 offset = MathUtil.multiplyVec(touchDirection, velocity);
        setPos(MathUtil.addVec(getPos(), offset));
    }
}
