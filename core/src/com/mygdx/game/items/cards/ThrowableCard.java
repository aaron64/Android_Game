package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.ElementType;
import com.mygdx.game.attributes.QualityType;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;

public class ThrowableCard extends AttackCard {

    public ThrowableCard(String name, String folder, int damage, CardType type, QualityType quality, ElementType element) {
        super(name, folder, "Throw a projectile", damage, type, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {

    }
}
