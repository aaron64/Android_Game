package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.entities.battle.misc.MagicProjectile;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2i;

public class MagicCard extends AttackCard {

    public MagicCard(String name, int damage, Quality quality, Element element) {
        super(name, "magic", "A magic projectile", damage, CardType.MAGIC, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        scene.addEntity(new MagicProjectile(scene, new Vector2i(user.getIndexPos()), getDamage(), user));
    }
}
