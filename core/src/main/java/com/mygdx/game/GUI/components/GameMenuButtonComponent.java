package com.mygdx.game.GUI.components;

import com.mygdx.game.GUI.GUI;
import com.mygdx.game.Game;
import com.mygdx.game.util.Vector2f;

public class GameMenuButtonComponent extends ButtonComponent {

    public GameMenuButtonComponent(GUI gui, Vector2f pos) {
        super(gui, "GAME_MENU_BUTTON", pos);
    }

    @Override
    public void onClick() {
        Game.pushScene(Game.gameMenuScene);
    }
}
