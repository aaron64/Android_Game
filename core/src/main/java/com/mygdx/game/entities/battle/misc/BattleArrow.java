package com.mygdx.game.entities.battle.misc;


import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.items.cards.BowCard;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vec2i;

public class BattleArrow extends BattleEntity {

    private float xv;
    private BattleLiving user;

    private BowCard card;

    public BattleArrow(SceneBattle scene, BowCard card, Vec2i indexPos, BattleLiving user) {
        super(scene, indexPos, "misc/arrow");
        moveY(scene.getGrid().getTile(0,0).getSize().y/2);

        this.card = card;

        this.user = user;
        setSize(new Vec2i(180, 27));

        xv = 45;
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
        BattleEntity e = null;

        if(tile != null)
            e = tile.getEntity();

        if(e != null && e != user) {
            e.hit(card.calculateDamage(e), card.getElement());
            scene.removeEntity(this);
        }
    }
}
