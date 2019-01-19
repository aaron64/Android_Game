package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.scenes.battle.SceneBattle;
import com.mygdx.game.util.Vector2f;

public class BattleGoToSelectButton extends ButtonComponent {

    private SceneBattle scene;
    public BattleGoToSelectButton(GUI gui, Vector2f pos, SceneBattle scene) {
        super(gui, "BATTLE_GO_TO_SELECTION_BUTTON", pos);

        this.scene = scene;
        off();
    }

    @Override
    public void onClick() {
        scene.goToSelection();
    }
}
