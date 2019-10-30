package com.mygdx.game.animation;

import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vec2f;

/**
 *
 */
public class BattleTileBounceAnimation extends Animation {

    private SceneBattleTile tile;
    private Vec2f origionalPos;
    private int time, duration;
    private float amount;

    public BattleTileBounceAnimation(SceneBattleTile tile, float amount, int duration) {
        super(false, true, "TILE_BOUNCE");

        origionalPos = new Vec2f(tile.getPos());

        this.amount = amount;
        this.tile = tile;
        this.duration = duration;
    }

    @Override
    public void update(Scene scene) {
        time++;

        origionalPos = tile.getPos();
        origionalPos.y += Math.sin((float)(time/duration)*Math.PI) * amount;
        tile.setPos(origionalPos);
    }
}
