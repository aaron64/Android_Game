package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;

public abstract class AttackCard extends Card {

    private int damage;
    public AttackCard(String name, String folder, String description, int damage, CardType type, Quality quality, Element element) {
        super(name, folder, description, type, quality, element);
        this.damage = (int)(damage * getQuality().getMultiplier());
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
