package com.mygdx.game.entities.battle.misc;


import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.TimedSpriteSheet;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.items.cards.MagicCard;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vector2i;

public class MagicProjectile extends BattleEntity {

    private float xv;
    private BattleLiving user;

    private TimedSpriteSheet spriteSheet;

    private MagicCard card;

    public MagicProjectile(SceneBattle scene, MagicCard card, Vector2i indexPos, BattleLiving user) {
        super(scene, indexPos, "misc/magic_projectile");
        moveY(scene.getGrid().getTile(0,0).getSize().y/2);

        this.card = card;

        this.user = user;

        xv = 20;
        if(user instanceof BattleEnemy)
            xv *= -1;

        spriteSheet = new TimedSpriteSheet(getImage(), 4, 4);

        getSize().y *= 4;

        if(user instanceof BattleEnemy)
            getSize().x *= -1;
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

        if(e != null && e instanceof BattleLiving && e != user) {
            e.hit(card.calculateDamage(e), card.getElement());
            scene.removeEntity(this);
        }

        spriteSheet.update();
    }

    @Override
    public void render(RenderSystem rs) {
        rs.setColor(card.getElement().getColor());
        spriteSheet.render(rs, getPos(), getSize());
        rs.resetColor();
    }
}
