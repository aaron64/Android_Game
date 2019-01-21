package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.FontUtil;

public class HealCard extends PotionCard {

    private int healAmount;
    public HealCard(String name, int amount, int pointsCost, Quality quality) {
        super(name, "Heals the player", CardType.SUPPORT, pointsCost, quality, null);
        this.name = name;
        healAmount = (int) (amount * quality.getMultiplier());

        amountSize = FontUtil.getTextSize(amountFont, "" + getAmount());
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        scene.getPlayer().recover(healAmount);
    }

    @Override
    public int getAmount() {
        return healAmount;
    }
}
