package com.mygdx.game.items.cards;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
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

        amountSize = FontUtil.getTextSize(amountFont, "" + getAmount());

        String qualityStr = quality.getStr();
        if(quality != Quality.STANDARD)
            qualityStr = "_" + qualityStr;
        overlay_texture = new Texture("items/cards/potions/Potion" + qualityStr + "_overlay.png");
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        user.recover(healAmount);
    }

    @Override
    public void drawIcon(RenderSystem rs, Vector2f pos, Vector2i size) {
        rs.draw(icon, pos, size);
        rs.setColor(Element.FIRE.getColor());
        rs.draw(overlay_texture, pos, size);
        rs.resetColor();
    }

    @Override
    public int getAmount() {
        return healAmount;
    }
}
