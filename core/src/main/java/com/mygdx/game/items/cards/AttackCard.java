package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.util.FontUtil;

public abstract class AttackCard extends Card {

    private int damage;
    public AttackCard(String name, int lockInitial, int lockFinal, String folder, String description, int damage, CardType type, int pointsCost, Quality quality, Element element) {
        super(name, lockInitial, lockFinal, folder, description, type, pointsCost, quality, element);
        this.damage = (int)(damage * getQuality().getMultiplier());

        amountSize = FontUtil.getTextSize(amountFont, getAmount());
    }

    public int getDamage() {
        return damage;
    }

    public int calculateDamage(BattleEntity entity) {
        int baseDamage = damage;
        if(entity instanceof BattleLiving) {
            if(((BattleLiving)entity).getElement() != null)
                baseDamage *= ((BattleLiving)entity).getElement().getMultiplier(getElement());
        }

        return baseDamage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String getAmount() {
        return damage + "";
    }
}
