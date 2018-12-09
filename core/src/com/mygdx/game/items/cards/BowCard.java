package com.mygdx.game.items.cards;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.entities.battle.misc.BattleArrow;
import com.mygdx.game.scenes.battle.SceneBattle;


public class BowCard extends AttackCard {

    public BowCard(String name, int damage) {
        super(name, "bows", damage, CardType.BOW);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        scene.addEntity(new BattleArrow(user.getIndexPos(), scene.getGrid(), getDamage(), user));

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
