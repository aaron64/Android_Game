package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.entities.battle.misc.MagicProjectile;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.graphics.TimedSpriteSheet;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class MagicCard extends AttackCard {

    TimedSpriteSheet spriteSheet, spriteSheetOverlay;

    public MagicCard(String name, int lockInitial, int lockFinal, int damage, int pointsCost, Quality quality, Element element) {
        super(name, lockInitial, lockFinal, "magic", "A magic projectile", damage, CardType.MAGIC, pointsCost, quality, element);


        spriteSheet = new TimedSpriteSheet(getIcon(), 4, 10);
        spriteSheetOverlay = new TimedSpriteSheet(overlay_texture, 4, 10);
        if(element == null) {
            setElement(Element.getRandomElement(false));
        }
    }

    @Override
    public void drawIcon(RenderSystem rs, Vector2f pos, Vector2i size, float alpha) {
        spriteSheet.update();
        spriteSheetOverlay.update();

        rs.setColor(1f, 1f, 1f, alpha);
        spriteSheet.render(rs, pos, size);
        if(getElement() != null) {
            rs.setColor(getElement().getColor(), alpha);
            spriteSheetOverlay.render(rs, pos, size);
        }
        rs.resetColor();
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        scene.addEntity(new MagicProjectile(scene, this, new Vector2i(user.getIndexPos()), user));
    }
}
