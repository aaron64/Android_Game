package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.items.cards.AttackCard;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2f;

public abstract class ThrowableCard extends AttackCard {

    private ThrowableSize size;
    private int range;

    public ThrowableCard(String name, int damage, ThrowableSize size, int range, int pointsCost, Quality quality, Element element) {
        super(name, "throwables", "Throw a projectile", damage, CardType.THROWABLE, pointsCost, quality, element);

        this.size = size;
        this.range = range;
    }

    public ThrowableSize getImpactSize() {
        return size;
    }
}
