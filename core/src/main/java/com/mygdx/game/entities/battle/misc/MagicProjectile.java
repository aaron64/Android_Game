package com.mygdx.game.entities.battle.misc;


import com.mygdx.game.attributes.Element;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.TimedSpriteSheet;
import com.mygdx.game.graphics.Window;
import com.mygdx.game.items.cards.MagicCard;
import com.mygdx.game.lighting.SpotLight;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vector2i;

public class MagicProjectile extends BattleEntity {

    private float xv;
    private BattleLiving user;

    private TimedSpriteSheet spriteSheet;
    private TimedSpriteSheet overlaySheet;

    private MagicCard card;

    private SpotLight light;

    public MagicProjectile(SceneBattle scene, MagicCard card, Vector2i indexPos, BattleLiving user) {
        super(scene, indexPos, "misc/magic_projectile");
        moveY(scene.getGrid().getTile(0,0).getSize().y/2);

        light = new SpotLight(this, 256, card.getElement().getHighlight());
        scene.getLightEngine().addLight(light);

        this.card = card;

        this.user = user;

        xv = 20;
        if(user instanceof BattleEnemy)
            xv *= -1;

        spriteSheet = new TimedSpriteSheet(getImage(), 30, 2);
        overlaySheet = new TimedSpriteSheet(Image.getImage("entities/battle/misc/magic_projectile_overlay"), 30, 2);

        setSize(new Vector2i(spriteSheet.getSrcSize()));
        getSize().multiply(6);

        if(user instanceof BattleEnemy)
            getSize().x *= -1;
    }

    @Override
    public void update() {
        moveX(xv);
        refreshIndexPos();

        if(!Window.inWindow(this)) {
            remove();
        }

        SceneBattleTile tile = scene.getGrid().getTile(getIndexPos());
        BattleEntity e = null;

        if(tile != null)
            e = tile.getEntity();

        if(e != null && e instanceof BattleLiving && e != user) {
            e.hit(card.calculateDamage(e), card.getElement());
            remove();
        }

        spriteSheet.update();
        overlaySheet.update();
    }

    private void remove() {
        scene.removeEntity(this);
        scene.getLightEngine().removeLight(light);
    }

    @Override
    public void render(RenderSystem rs) {

        Element.drawTextureWithOverlay(rs, spriteSheet, overlaySheet, getPos(), getSize(), card.getElement());

    }
}
