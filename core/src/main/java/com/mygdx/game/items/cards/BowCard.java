package com.mygdx.game.items.cards;


import com.mygdx.game.attributes.Element;
import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.entities.battle.misc.BattleArrow;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vec2i;


public class BowCard extends AttackCard {

    public BowCard(String name, int lockInitial, int lockFinal, int damage, int pointsCost, Quality quality, Element element) {
        super(name, lockInitial, lockFinal, "bows", "Shoot a bow", damage, CardType.BOW, pointsCost, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        scene.addEntity(new BattleArrow(scene, this, new Vec2i(user.getIndexPos()), user));

        Vec2i indexPos = user.getIndexPos();

        if(user.facingRight()) {
            for (int i = (int) (indexPos.x + 1); i < scene.getGrid().getWidth(); i++) {
                scene.getGrid().getTile(i, (int) indexPos.y).lightUp(getInitialLock());
            }
        } else {
            for (int i = (int) (indexPos.x - 1); i >= 0; i--) {
                scene.getGrid().getTile(i, (int) indexPos.y).lightUp(getInitialLock());
            }
        }
    }
}
