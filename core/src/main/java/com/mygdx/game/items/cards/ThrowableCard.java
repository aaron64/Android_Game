package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;

public abstract class ThrowableCard extends AttackCard {

    private ThrowableSize size;
    private int range;

    public ThrowableCard(String name, int lockInitial, int lockFinal, int damage, ThrowableSize size, int range, int pointsCost, Quality quality, Element element) {
        super(name, lockInitial, lockFinal, "throwables", "Throw a projectile", damage, CardType.THROWABLE, pointsCost, quality, element);

        this.size = size;
        this.range = range;
    }

    public ThrowableSize getImpactSize() {
        return size;
    }
}
