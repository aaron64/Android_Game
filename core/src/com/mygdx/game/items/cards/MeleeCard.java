package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.ElementType;
import com.mygdx.game.attributes.QualityType;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vector2i;

public class MeleeCard extends AttackCard {

    private int atkWidth;
    private int atkHeight;
    public MeleeCard(String name, int damage, int width, int height, QualityType quality, ElementType element) {
        super(name, "melee", "Hit an enemy", damage, CardType.MELEE, quality, element);
        atkWidth = width;
        atkHeight = height;
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        Vector2i indexPos = user.getIndexPos();
        int face = -1;
        if(user.facingRight()) {
            face = 1;
        }

        for(int i = 1; i < atkWidth+1; i++) {
            for(int j = 0; j < atkHeight; j++) {
                SceneBattleTile tile = scene.getTile(indexPos.x + (i*face), indexPos.y + (j*face));
                if(tile != null) {
                    tile.lightUp(20);

                    BattleEntity e = (BattleEntity) tile.getEntity();
                    if(e != null && e != user)
                        e.hit(getDamage());
                }
            }
        }
        user.lockFor(25);
    }
}
