package com.mygdx.game.attributes;

import com.mygdx.game.Game;
import com.mygdx.game.entities.battle.BattleEnemy;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2i;

public class ElementStateWind extends ElementState {

    public ElementStateWind(BattleLiving affected) {
        super(affected);
        SceneBattle scene = (SceneBattle)(Game.getCurrentScene());

        Vector2i newPos = new Vector2i(affected.getIndexPos());
        if(affected instanceof BattleEnemy) {
            newPos.x++;
            if(scene.getGrid().isFreeTile(newPos)) {
                affected.moveRight();
            }
        } else {
            newPos.x--;
            if(scene.getGrid().isFreeTile(newPos)) {
                affected.moveLeft();
            }
        }

        affected.setElementState(null);
    }

    @Override
    public void update() {

    }
}
