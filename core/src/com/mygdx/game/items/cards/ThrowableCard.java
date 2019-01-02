package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;

public class ThrowableCard extends AttackCard {

    public ThrowableCard(String name, String folder, int damage, CardType type, Quality quality, Element element) {
        super(name, folder, "Throw a projectile", damage, type, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {

    }
}
