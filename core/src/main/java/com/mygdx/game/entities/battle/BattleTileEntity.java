package com.mygdx.game.entities.battle;

import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleTile;

public abstract class BattleTileEntity extends BattleEntity {

    public BattleTileEntity(com.mygdx.game.scenes.battle.SceneBattle scene, SceneBattleTile tile, String name) {
        super(scene, tile, name);
    }
}
