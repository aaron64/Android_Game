package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.ElementType;
import com.mygdx.game.attributes.QualityType;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;

public abstract class AttackCard extends Card {

    private int damage;
    public AttackCard(String name, String folder, int damage, CardType type, QualityType quality, ElementType element) {
        super(name, folder, type, quality, element);
        this.damage = (int)(damage * getQuality().getMultiplier());
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
