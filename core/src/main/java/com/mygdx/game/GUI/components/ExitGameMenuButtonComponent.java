package com.mygdx.game.GUI.components;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.GUI.GUI;
import com.mygdx.game.Game;
import com.mygdx.game.util.Vector2f;

public class ExitGameMenuButtonComponent extends ButtonComponent {

    public ExitGameMenuButtonComponent(GUI gui, Vector2f pos) {
        super(gui, "EXIT_GAME_MENU_BUTTON", pos);
    }

    @Override
    public void onClick() {
        Gdx.app.log("INFO", "EXITING SCENE YEAHHH");
        Game.endScene();
    }
}
