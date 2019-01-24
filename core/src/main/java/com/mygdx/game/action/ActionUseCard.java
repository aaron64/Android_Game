package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.battle.SceneBattle;

public class ActionUseCard extends Action {

    private SceneBattle scene;
    private Card card;
    public ActionUseCard(BattleEntity user, SceneBattle scene, Card card) {
        super(user);

        this.scene = scene;
        this.card = card;
    }

    @Override
    public void start() {
        card.use(scene, (BattleLiving)user);
        finished = true;
    }

    @Override
    public void update() {
        super.update();
    }
}
