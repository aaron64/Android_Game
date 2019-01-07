package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2i;

public class GunCard extends AttackCard {

    public GunCard(String name, int damage, int pointsCost, Quality quality, Element element) {
        super(name, "guns", "Fire a projectile", damage, CardType.GUN, pointsCost, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        BattleEntity hitEntity = user.getDirectLineOfSight();
        if(hitEntity != null) {
            hitEntity.hit(getDamage(), getElement());
        }

        Vector2i indexPos = user.getIndexPos();

        if(user.facingRight()) {
            for (int i = indexPos.x + 1; i < scene.getGrid().getWidth(); i++) {
                scene.getGrid().getTile(i, indexPos.y).lightUp(25);
            }
        } else {
            for (int i = indexPos.x - 1; i >= 0; i--) {
                scene.getGrid().getTile(i, indexPos.y).lightUp(25);
            }
        }
    }
}
