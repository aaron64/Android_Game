package com.mygdx.game.items.cards;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.attributes.ElementType;
import com.mygdx.game.attributes.QualityType;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;

public class GunCard extends AttackCard {

    public GunCard(String name, int damage, QualityType quality, ElementType element) {
        super(name, "guns", damage, CardType.GUN, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        BattleEntity hitEntity = user.getDirectLineOfSight();
        if(hitEntity != null) {
            hitEntity.hit(getDamage());
        }

        Vector2 indexPos = user.getIndexPos();

        if(user.facingRight()) {
            for (int i = (int) (indexPos.x + 1); i < scene.getGrid().getWidth(); i++) {
                scene.getGrid().getTile(i, (int) indexPos.y).lightUp(25);
            }
        } else {
            for (int i = (int) (indexPos.x - 1); i >= 0; i--) {
                scene.getGrid().getTile(i, (int) indexPos.y).lightUp(25);
            }
        }
    }
}
