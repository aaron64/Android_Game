package com.mygdx.game.entities.battle.misc;

import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class Bomb extends BattleEntity {

    private int throwTime;
    private int throwTimeCounter;
    private Vector2f velocity;
    private float angle;
    private float distance;

    private int damage;
    private BattleLiving user;

    public Bomb(SceneBattle scene, Vector2i indexPos, int damage, BattleLiving user, float distance) {
        super(scene, indexPos, "misc/bomb");
        moveY(scene.getGrid().getTile(0,0).getSize().y/2);

        this.damage = damage;

        this.user = user;
        setSize(new Vector2i(180, 27));

        this.distance = distance;
        throwTime = 100;
        throwTimeCounter = 0;

        velocity = new Vector2f(0,0);
        velocity.x = throwTime / distance;


    }

    @Override
    public void update() {

    }

    public void explode() {

    }
}
