package com.mygdx.game.items.cards;


import com.mygdx.game.attributes.ElementType;
import com.mygdx.game.attributes.QualityType;
import com.mygdx.game.entities.Entity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.entities.battle.misc.BattleArrow;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2i;


public class BowCard extends AttackCard {

    public BowCard(String name, int damage, QualityType quality, ElementType element) {
        super(name, "bows", damage, CardType.BOW, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        scene.addEntity(new BattleArrow(scene, new Vector2i(user.getIndexPos()), getDamage(), user));

        Vector2i indexPos = user.getIndexPos();

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
