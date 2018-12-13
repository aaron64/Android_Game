package com.mygdx.game.entities.battle.misc;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Window;

public class BattleArrow extends BattleEntity {

    private float xv;
    private int damage;
    private BattleLiving user;

    public BattleArrow(SceneBattle scene, Vector2 indexPos, int damage, BattleLiving user) {
        super(scene, indexPos, "misc/arrow");
        moveY(scene.getGrid().getTile(0,0).getSize().y/2);

        this.damage = damage;

        this.user = user;
        setSize(new Vector2(180, 27));

        xv = 25;
        if(user instanceof BattleEnemy)
            xv *= -1;
    }

    @Override
    public void update() {
        moveX(xv);
        refreshIndexPos();

        if(!Window.inWindow(this)) {
            scene.removeEntity(this);
        }

        SceneBattleTile tile = scene.getGrid().getTile(getIndexPos());
        Entity e = null;

        if(tile != null)
            e = tile.getEntity();

        if(e != null && e instanceof BattleLiving && e != user) {
            ((BattleLiving)e).hit(damage);
            scene.removeEntity(this);
        }
    }
}
