package com.mygdx.game.entities.battle.misc;

import com.mygdx.game.Game;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.items.cards.ThrowableSize;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class Bomb extends BattleEntity {

    private int throwTime;
    private int throwTimeCounter;
    private Vector2f velocity;
    private float angle;
    private float distance;

    private float yTrigger;

    private int damage;
    private BattleLiving user;

    public Bomb(SceneBattle scene, Vector2i indexPos, Vector2i dest, int damage, ThrowableSize size, BattleLiving user) {
        super(scene, indexPos, "misc/bomb");
        moveY(scene.getGrid().getTile(0,0).getSize().y/2);

        yTrigger = getPos().y - 1;

        this.damage = damage;

        this.user = user;
        //setSize(new Vector2i(180, 27));

        this.distance = scene.getGrid().getAbsoluteTilePosition(dest).x - scene.getGrid().getAbsoluteTilePosition(indexPos).x;
        throwTime = 15;
        throwTimeCounter = 0;

        velocity = new Vector2f(0,0);

        float vMag = distance / throwTime;

        float stuff = (float) ((2 * Game.getGravity() * distance / (vMag * vMag)/2)%(2*Math.PI));
        float theta = (float) Math.asin(((2 * Game.getGravity() * distance / (vMag * vMag)/2)%(2*Math.PI)));

        velocity.x = (float) (vMag * Math.cos(theta));
        velocity.y = (float) (vMag * Math.sin(theta)) * -1;

        float scaleVel = vMag / velocity.x;

        velocity.x *= scaleVel;
        velocity.y *= scaleVel;
    }

    @Override
    public void update() {
        throwTimeCounter++;
        move(velocity);
        velocity.y += Game.getGravity();

        if(getPos().y < yTrigger)
            scene.removeEntity(this);
    }

    public void explode() {

    }
}
