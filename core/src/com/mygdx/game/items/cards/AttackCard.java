package com.mygdx.game.items.cards;

import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;

public abstract class AttackCard extends Card {

    private int damage;
    public AttackCard(String name, String folder, int damage, CardType type) {
        super(name, folder, type);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
