package com.mygdx.game.animation;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vector2f;

public class BattleMoveAnimation extends Animation {

    private Vector2f from, to;
    private SceneBattleTile oldTile, newTile;
    private BattleLiving entity;
    private int speed;
    private int pos = 0;

    public BattleMoveAnimation(SceneBattleTile oldTile, SceneBattleTile newTile, BattleLiving entity) {
        super(false, true, "BATTLE_MOVE");
        this.from = entity.getPos();
        this.to = newTile.getPos();

        this.oldTile = oldTile;
        this.newTile = newTile;

        this.speed = entity.getMovementSpeed();

        this.entity = entity;

        oldTile.removeEntity();

        newTile.setEntity(entity);
        entity.setIndexPos(newTile.getIndexPos());
    }

    @Override
    public void update(Scene scene) {
        float v = (float)pos/speed;
        v = (float)Math.sin(v * Math.PI / 2.0f);
        entity.getPos().x = (to.x * v) + (from.x * (1 - v));
        entity.getPos().y = (to.y * v) + (from.y * (1 - v));

        if(pos >= speed) {
            done = true;
        }

        pos++;
    }
}
