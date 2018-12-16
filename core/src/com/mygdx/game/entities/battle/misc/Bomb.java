package com.mygdx.game.entities.battle.misc;

import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class Bomb extends BattleEntity {

    private Vector2f velocity;
    private float yTrigger;
    private int damage;
    private BattleLiving user;

    public Bomb(SceneBattle scene, Vector2i indexPos, int damage, BattleLiving user) {
        super(scene, indexPos, "misc/bomb");
        moveY(scene.getGrid().getTile(0,0).getSize().y/2);
        yTrigger = getPos().y - 1;

        this.damage = damage;

        this.user = user;
        setSize(new Vector2i(180, 27));
    }

    @Override
    public void update() {
        if(getPos().y < yTrigger) {
            explode();
        }
    }

    public void explode() {

    }
}
