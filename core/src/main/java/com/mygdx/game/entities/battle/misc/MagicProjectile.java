package com.mygdx.game.entities.battle.misc;


import com.mygdx.game.attributes.Element;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.util.Vector2i;
import com.mygdx.game.graphics.Window;

public class MagicProjectile extends BattleEntity {

    private float xv;
    private int damage;
    private BattleLiving user;
    private Element element;

    public MagicProjectile(SceneBattle scene, Vector2i indexPos, int damage, BattleLiving user, Element element) {
        super(scene, indexPos, "misc/magic_projectile");
        moveY(scene.getGrid().getTile(0,0).getSize().y/2);

        this.damage = damage;

        this.user = user;

        xv = 10;
        if(user instanceof BattleEnemy)
            xv *= -1;

        this.element = element;
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
            ((BattleLiving)e).hit(damage, element);
            scene.removeEntity(this);
        }
    }

    @Override
    public void render(RenderSystem rs) {
        super.render(rs);
    }
}
