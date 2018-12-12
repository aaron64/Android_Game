package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.ElementType;
import com.mygdx.game.attributes.QualityType;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;

public class MagicCard extends AttackCard {

    public MagicCard(String name, int damage, QualityType quality, ElementType element) {
        super(name, "magic", damage, CardType.MAGIC, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {

    }
}
