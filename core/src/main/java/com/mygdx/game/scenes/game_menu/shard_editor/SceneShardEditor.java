package com.mygdx.game.scenes.game_menu.shard_editor;

import com.mygdx.game.GUI.components.ExitGameMenuButtonComponent;
import com.mygdx.game.scenes.Scene;
import com.mygdx.game.util.Vector2f;
import com.mygdx.game.graphics.Window;

public class SceneShardEditor extends Scene {

    public SceneShardEditor() {
        super();

        Vector2f menuButtonPos = new Vector2f(Window.percRight(0.05f), Window.percTop(0.05f));
        gui.addComponent(new ExitGameMenuButtonComponent(gui, menuButtonPos));

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render() {

    }

    @Override
    public void onPushed() {

    }

    @Override
    public void onPopped() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void dispose() {

    }
}
