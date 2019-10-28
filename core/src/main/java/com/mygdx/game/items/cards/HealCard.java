package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.graphics.Image;
import com.mygdx.game.graphics.RenderSystem;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.FontUtil;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class HealCard extends PotionCard {

    private int healAmount;
    public HealCard(String name, int lockInitial, int lockFinal, int amount, int pointsCost, Quality quality) {
        super(name, lockInitial, lockFinal, "Heals the player", CardType.SUPPORT, pointsCost, quality, null);
        this.name = name;
        healAmount = (int) (amount * quality.getMultiplier());

        amountSize = FontUtil.getTextSize(amountFont, getAmount());

        String qualityStr = quality.getStr();
        if(quality != Quality.STANDARD)
            qualityStr = "_" + qualityStr;
        overlay_texture = Image.getImage("items/cards/potions/Potion" + qualityStr + "_overlay");
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        user.recover(healAmount);
    }

    @Override
    public void drawIcon(RenderSystem rs, Vector2f pos, Vector2i size, float alpha) {
        rs.setColor(1, 1, 1, alpha);
        Element.drawTextureWithOverlay(rs, icon, overlay_texture, pos, size, Element.FIRE);
        rs.resetColor();
    }

    @Override
    public String getAmount() {
        return healAmount + "";
    }
}
