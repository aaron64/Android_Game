package com.mygdx.game.entities.battle;

import com.mygdx.game.attributes.Element;
import com.mygdx.game.entities.battle.BattleTileEntity;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.scenes.battle.SceneBattleGrid;
import com.mygdx.game.scenes.battle.SceneBattleTile;
import com.mygdx.game.util.Vector2i;

public class StillObject extends BattleTileEntity {

    int life;
    public StillObject(com.mygdx.game.scenes.battle.SceneBattle scene, SceneBattleTile tile, String name, int life) {
        super(scene, tile, name);

        getSize().multiply(tile.getSize().w() / getSize().w());

        this.life = life;
    }

    @Override
    public void update() {

    }

    @Override
    public void hit(int dmg, Element hitElement) {
        life -= dmg;
        if(life <= 0) {
            scene.getTile(getIndexPos()).removeEntity();
        }
    }
}
