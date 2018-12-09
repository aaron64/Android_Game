package com.mygdx.game.entities.battle.misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Window;

public class BattleArrow extends BattleEntity {

    private float xv;
    private int damage;
    private BattleLiving user;

    public BattleArrow(Vector2 indexPos, SceneBattleGrid grid, int damage, BattleLiving user) {
        super(indexPos, "misc/arrow", grid);
        moveY(grid.getTile(0,0).getSize().y/2);

        this.damage = damage;

        this.user = user;
        setSize(new Vector2(180, 27));

        xv = 25;
        if(user instanceof BattleEnemy)
            xv *= -1;
    }

    @Override
    public void update(Scene scene) {
        moveX(xv);
        refreshIndexPos(((SceneBattle)scene).getGrid());

        if(!Window.inWindow(this)) {
            scene.removeEntity(this);
        }

        SceneBattleTile tile = getGrid().getTile(getIndexPos());
        Entity e = null;

        if(tile != null)
            e = tile.getEntity();

        if(e != null && e instanceof BattleLiving && e != user) {
            ((BattleLiving)e).hit(damage, (SceneBattle) scene);
            scene.removeEntity(this);
        }
    }
}
