package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2f;

public class ThrowableCard extends AttackCard {

    private ThrowableSize size;

    public ThrowableCard(String name, String folder, int damage, CardType type, int pointsCost, Quality quality, Element element) {
        super(name, folder, "Throw a projectile", damage, type, pointsCost, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {

    }
}
