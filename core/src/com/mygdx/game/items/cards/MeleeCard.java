package com.mygdx.game.items.cards;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.attributes.ElementType;
import com.mygdx.game.attributes.QualityType;
import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;

public class MeleeCard extends AttackCard {

    private int atkWidth;
    private int atkHeight;
    public MeleeCard(String name, int damage, int width, int height, QualityType quality, ElementType element) {
        super(name, "swords", damage, CardType.MELEE, quality, element);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        Vector2 playerIndex = scene.getPlayer().getIndexPos();

        for(int i = 0; i < atkWidth; i++) {
            for(int j = 0; j < atkHeight; j++) {
                SceneBattleTile tile = scene.getTile((int)playerIndex.x + i, (int)playerIndex.y + j);
                if(tile.getEntity() != null) {
                    BattleEntity e = (BattleEntity) tile.getEntity();
                }
            }
        }
    }
}
