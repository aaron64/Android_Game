package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.entities.battle.misc.Bomb;
import com.mygdx.game.items.cards.ThrowableCard;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2i;

public class BombCard extends ThrowableCard {

    public BombCard(String name, int damage, ThrowableSize size, int range, int pointsCost, Quality quality, Element element) {
        super(name, damage, size, range, pointsCost, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        Vector2i dest = new Vector2i(user.getIndexPos());
        if(user instanceof BattleEnemy) {
            dest.subtract(2, 0);
        } else {
            dest.add(2, 0);
        }

        scene.addEntity(new Bomb(scene, user.getIndexPos(), dest, getDamage(), getImpactSize(), user));
    }
}
