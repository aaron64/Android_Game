package com.mygdx.game.action;

import com.mygdx.game.entities.battle.BattleEntity;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.items.cards.Card;
import com.mygdx.game.scenes.battle.SceneBattle;

/**
 * ActionUseCard
 *
 * This action is queued upon card use
 * when started, the card is used
 *
 * @author  Aaron Chambers
 */
public class ActionUseCard extends Action {

    private SceneBattle scene;
    private Card card;

    /**
     * ActionUseCard constructor
     * @param user The BattleEntity being assigned the action
     * @param scene Instance of BattleScene, for card use
     * @param card The card being used
     */
    public ActionUseCard(BattleEntity user, SceneBattle scene, Card card) {
        super(user);

        this.scene = scene;
        this.card = card;
    }

    /**
     * start
     * use the card, finish action
     */
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
