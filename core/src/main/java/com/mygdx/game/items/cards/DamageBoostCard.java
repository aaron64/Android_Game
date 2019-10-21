package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.entities.battle.BattlePlayer;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class DamageBoostCard extends PotionCard {

    private float boost;

    public DamageBoostCard(String name, int lockInitial, int lockFinal, int pointsCost, Quality quality) {
        super(name, lockInitial, lockFinal, "Boosts next card's damage", CardType.SUPPORT, pointsCost, quality, null);
        this.name = name;

        boost = quality.getMultiplier() + 1;

        amountSize = FontUtil.getTextSize(amountFont, getAmount());

        String qualityStr = quality.getStr();
        if(quality != Quality.STANDARD)
            qualityStr = "_" + qualityStr;
        overlay_texture = Image.getImage("items/cards/potions/Potion" + qualityStr + "_overlay");
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        if(user instanceof BattlePlayer) {
            Card card = ((BattlePlayer)user).getCards().getNextCard();
            if(card != null && card instanceof AttackCard) {
                ((AttackCard)card).setDamage((int)(((AttackCard) card).getDamage() * boost));
            }
        }
    }

    @Override
    public void drawIcon(RenderSystem rs, Vector2f pos, Vector2i size) {
        rs.draw(icon, pos, size);
        rs.setColor(Element.FIRE.getColor());
        rs.draw(overlay_texture, pos, size);
        rs.resetColor();
    }

    @Override
    public String getAmount() {
        return boost + "";
    }
}
