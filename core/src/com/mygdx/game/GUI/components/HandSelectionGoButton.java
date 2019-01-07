package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.scenes.battle.hand_select.SceneHandSelect;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.util.Vector2i;

public class HandSelectionGoButton extends ButtonComponent {

    private SceneHandSelect scene;

    public HandSelectionGoButton(GUI gui, Vector2f pos, Vector2i size, SceneHandSelect scene) {
        super(gui, "HAND_GO_BUTTON", pos);
        this.size = size;
        this.scene = scene;
    }

    @Override
    public void onClick() {
        scene.go();
    }
}
