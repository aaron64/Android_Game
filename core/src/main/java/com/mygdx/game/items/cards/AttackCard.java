package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.util.FontUtil;

public abstract class AttackCard extends Card {

    private int damage;
    public AttackCard(String name, String folder, String description, int damage, CardType type, int pointsCost, Quality quality, Element element) {
        super(name, folder, description, type, pointsCost, quality, element);
        this.damage = (int)(damage * getQuality().getMultiplier());

        amountSize = FontUtil.getTextSize(amountFont, "" + getAmount());
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getAmount() {
        return damage;
    }
}
