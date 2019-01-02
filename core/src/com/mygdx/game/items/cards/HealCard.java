package com.mygdx.game.items.cards;

import com.mygdx.game.attributes.Quality;
import com.mygdx.game.entities.battle.BattleLiving;
import com.mygdx.game.scenes.battle.SceneBattle;

public class HealCard extends Card {

    private int healAmount;
    public HealCard(String name) {
        super(name, "misc", "Heals the player", CardType.SUPPORT, Quality.STANDARD, null);
    }

    @Override
    public void use(SceneBattle scene, BattleLiving user) {
        scene.getPlayer().recover(healAmount);
    }
}
